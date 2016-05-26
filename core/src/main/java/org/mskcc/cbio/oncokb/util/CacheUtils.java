package org.mskcc.cbio.oncokb.util;

import org.mskcc.cbio.oncokb.model.Alteration;
import org.mskcc.cbio.oncokb.model.Evidence;
import org.mskcc.cbio.oncokb.model.Gene;
import org.mskcc.cbio.oncokb.model.TumorType;

import java.util.*;


/**
 * Created by Hongxin on 4/1/16.
 * <p/>
 * CacheUtils is used to manage cached variant summaries, relevant alterations, alterations which all gene based.
 * It also includes mapped tumor types which is based on query tumor type name + source.
 * <p/>
 * The GeneObservable manages all gene based caches. Any updates happen on gene will automatically trigger
 * GeneObservable to notify all observers to update relative cache.
 * <p/>
 * TODO:
 * Ideally, we should place cache functions in the cache BAO with a factory which controls the source of data.
 * In this way, user can easily to choose to get data from cache or database directly.
 */


public class CacheUtils {
    private static Map<String, Map<String, String>> variantSummary = new HashMap<>();
    private static Map<String, Map<String, String>> variantCustomizedSummary = new HashMap<>();
    private static Map<String, Map<String, List<Alteration>>> relevantAlterations = new HashMap<>();
    private static Map<String, List<Alteration>> alterations = new HashMap<>();
    private static Map<String, List<TumorType>> mappedTumorTypes = new HashMap<>();
    private static Map<Integer, Gene> genesByEntrezId = new HashMap<>();
    private static Map<String, Gene> genesByHugoSymbol = new HashMap<>();
    private static Map<String, Map<String, List<Evidence>>> relevantEvidences = new HashMap<>();
    private static String status = "enabled"; //Current cacheUtils status. Applicable value: disabled enabled

    private static Observer variantSummaryObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                variantSummary.remove(operation.get("val"));
            } else if (operation.get("cmd") == "reset") {
                variantSummary.clear();
            }
        }
    };

    private static Observer variantCustomizedSummaryObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                variantCustomizedSummary.remove(operation.get("val"));
            } else if (operation.get("cmd") == "reset") {
                variantCustomizedSummary.clear();
            }
        }
    };

    private static Observer relevantAlterationsObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                relevantAlterations.remove(operation.get("val"));
            } else if (operation.get("cmd") == "reset") {
                relevantAlterations.clear();
            }
        }
    };

    private static Observer alterationsObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                alterations.remove(operation.get("val"));
            } else if (operation.get("cmd") == "reset") {
                alterations.clear();
            }
        }
    };

    private static Observer geneObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                String gene = operation.get("val");
                genesByEntrezId.remove(gene);
                genesByHugoSymbol.remove(gene);
            } else if (operation.get("cmd") == "reset") {
                genesByEntrezId.clear();
                genesByHugoSymbol.clear();
            }
        }
    };

    private static Observer relevantEvidencesObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Map<String, String> operation = (Map<String, String>) arg;
            if (operation.get("cmd") == "update") {
                relevantEvidences.remove(operation.get("val"));
            } else if (operation.get("cmd") == "reset") {
                relevantEvidences.clear();
            }
        }
    };

    static {
        GeneObservable.getInstance().addObserver(variantSummaryObserver);
        GeneObservable.getInstance().addObserver(variantCustomizedSummaryObserver);
        GeneObservable.getInstance().addObserver(relevantAlterationsObserver);
        GeneObservable.getInstance().addObserver(alterationsObserver);
        GeneObservable.getInstance().addObserver(geneObserver);
        GeneObservable.getInstance().addObserver(relevantEvidencesObserver);
    }

    public static Gene getGeneByEntrezId(Integer entrezId) {
        if (genesByEntrezId.containsKey(entrezId)) {
            return genesByEntrezId.get(entrezId);
        } else {
            return null;
        }
    }

    public static Boolean containGeneByEntrezId(Integer entrezId) {
        return genesByEntrezId.containsKey(entrezId) ? true : false;
    }

    public static void setGeneByEntrezId(Integer entrezId, Gene gene) {
        genesByEntrezId.put(entrezId, gene);
    }

    public static Gene getGeneByHugoSymbol(String hugoSymbol) {
        if (genesByHugoSymbol.containsKey(hugoSymbol)) {
            return genesByHugoSymbol.get(hugoSymbol);
        } else {
            return null;
        }
    }

    public static Boolean containGeneByHugoSymbol(String hugoSymbol) {
        return genesByHugoSymbol.containsKey(hugoSymbol) ? true : false;
    }

    public static void setGeneByHugoSymbol(String hugoSymbol, Gene gene) {
        genesByHugoSymbol.put(hugoSymbol, gene);
    }

    public static List<Evidence> getRelevantEvidences(String gene, String variant) {
        if (relevantEvidences.containsKey(gene) && relevantEvidences.get(gene).containsKey(variant)) {
            return relevantEvidences.get(gene).get(variant);
        } else {
            return null;
        }
    }

    public static Boolean containRelevantEvidences(String gene, String variant) {
        return (relevantEvidences.containsKey(gene) &&
                relevantEvidences.get(gene).containsKey(variant)) ? true : false;
    }

    public static void setRelevantEvidences(String gene, String variant, List<Evidence> evidences) {
        if (!relevantEvidences.containsKey(gene)) {
            relevantEvidences.put(gene, new HashMap<String, List<Evidence>>());
        }
        relevantEvidences.get(gene).put(variant, evidences);
    }

    public static String getVariantSummary(String gene, String variant) {
        if (variantSummary.containsKey(gene) && variantSummary.get(gene).containsKey(variant)) {
            return variantSummary.get(gene).get(variant);
        } else {
            return null;
        }
    }

    public static Boolean containVariantSummary(String gene, String variant) {
        return (variantSummary.containsKey(gene) &&
                variantSummary.get(gene).containsKey(variant)) ? true : false;
    }

    public static void setVariantSummary(String gene, String variant, String summary) {
        if (!variantSummary.containsKey(gene)) {
            variantSummary.put(gene, new HashMap<String, String>());
        }
        variantSummary.get(gene).put(variant, summary);
    }

    public static String getVariantCustomizedSummary(String gene, String variant) {
        if (variantCustomizedSummary.containsKey(gene) && variantCustomizedSummary.get(gene).containsKey(variant)) {
            return variantCustomizedSummary.get(gene).get(variant);
        } else {
            return null;
        }
    }

    public static Boolean containVariantCustomizedSummary(String gene, String variant) {
        return (variantCustomizedSummary.containsKey(gene) &&
                variantCustomizedSummary.get(gene).containsKey(variant)) ? true : false;
    }

    public static void setVariantCustomizedSummary(String gene, String variant, String summary) {
        if (!variantCustomizedSummary.containsKey(gene)) {
            variantCustomizedSummary.put(gene, new HashMap<String, String>());
        }
        variantCustomizedSummary.get(gene).put(variant, summary);
    }

    public static List<Alteration> getRelevantAlterations(String gene, String variant) {
        if (relevantAlterations.containsKey(gene) && relevantAlterations.get(gene).containsKey(variant)) {
            return relevantAlterations.get(gene).get(variant);
        } else {
            return null;
        }
    }

    public static Boolean containRelevantAlterations(String gene, String variant) {
        return (relevantAlterations.containsKey(gene) &&
                relevantAlterations.get(gene).containsKey(variant)) ? true : false;
    }

    public static void setRelevantAlterations(String gene, String variant, List<Alteration> alts) {
        if (!relevantAlterations.containsKey(gene)) {
            relevantAlterations.put(gene, new HashMap<String, List<Alteration>>());
        }
        relevantAlterations.get(gene).put(variant, alts);
    }

    public static List<Alteration> getAlterations(String gene) {
        return alterations.get(gene);
    }

    public static Boolean containAlterations(String gene) {
        return alterations.containsKey(gene) ? true : false;
    }

    public static void setAlterations(String gene, List<Alteration> alts) {
        alterations.put(gene, alts);
    }

    public static List<TumorType> getMappedTumorTypes(String queryTumorType, String source) {
        return mappedTumorTypes.get(queryTumorType + "&" + source);
    }

    public static Boolean containMappedTumorTypes(String queryTumorType, String source) {
        return mappedTumorTypes.containsKey(queryTumorType + "&" + source) ? true : false;
    }

    public static void setMappedTumorTypes(String queryTumorType, String source, List<TumorType> tumorTypes) {
        mappedTumorTypes.put(queryTumorType + "&" + source, tumorTypes);
    }

    public static void updateGene(String gene) {
        GeneObservable.getInstance().update("update", gene);
    }

    public static void resetAll() {
        GeneObservable.getInstance().update("reset", null);
    }

    public static void enableCacheUtils() {
        status = "enabled";
    }

    public static void disableCacheUtils() {
        status = "disabled";
    }

    public static String getCacheUtilsStatus() {
        return status;
    }

    public static Boolean isEnabled() {
        if (status == "enabled") {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isDisabled() {
        if (status == "disabled") {
            return true;
        } else {
            return false;
        }
    }
}