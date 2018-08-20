/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.cbio.oncokb.dao;

import org.mskcc.cbio.oncokb.model.Alteration;
import org.mskcc.cbio.oncokb.model.AlterationType;
import org.mskcc.cbio.oncokb.model.Gene;
import org.mskcc.cbio.oncokb.model.VariantConsequence;

import java.util.List;

/**
 * @author jgao
 */
public interface AlterationDao extends GenericDao<Alteration, Integer> {

    /**
     * Get set of alterations by entrez gene Id.
     *
     * @param gene
     * @return
     */
    List<Alteration> findAlterationsByGene(Gene gene);

    /**
     * @param gene
     * @param alterationType
     * @param alteration
     * @return
     */
    Alteration findAlteration(Gene gene, AlterationType alterationType, String alteration);


    /**
     * @param gene
     * @param alterationType
     * @param alteration
     * @param name
     * @return
     */
    Alteration findAlteration(Gene gene, AlterationType alterationType, String alteration, String name);

    /**
     * @param gene
     * @param consequence
     * @param start
     * @param end
     * @return
     */
    List<Alteration> findMutationsByConsequenceAndPosition(Gene gene, VariantConsequence consequence, int start, int end);

    /**
     * @param gene
     * @param consequence
     * @param start
     * @param end
     * @return
     */
    List<Alteration> findMutationsByConsequenceAndPositionOnSamePosition(Gene gene, VariantConsequence consequence, int start, int end);
}
