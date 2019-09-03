package org.mskcc.cbio.oncokb.api.pvt;

import io.swagger.annotations.*;
import org.mskcc.cbio.oncokb.apiModels.AnnotatedVariant;
import org.mskcc.cbio.oncokb.apiModels.MatchVariantRequest;
import org.mskcc.cbio.oncokb.apiModels.MatchVariantResult;
import org.mskcc.cbio.oncokb.apiModels.VariantAnnotation;
import org.mskcc.cbio.oncokb.model.*;
import org.mskcc.cbio.oncokb.model.oncotree.TumorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hongxin on 12/12/16.
 */

@Api(tags = "Utils", description = "The utils API")
public interface PrivateUtilsApi {
    @ApiOperation(value = "", notes = "Get All Suggested Variants.", response = String.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AnnotatedVariant.class, responseContainer = "List")})
    @RequestMapping(value = "/utils/suggestedVariants", produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<String>> utilsSuggestedVariantsGet();

    @ApiOperation(value = "", notes = "Determine whether variant is hotspot mutation.", response = Boolean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Boolean.class)})
    @RequestMapping(value = "/utils/isHotspot", produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<Boolean> utilsHotspotMutationGet(
        @ApiParam(value = "Gene hugo symbol") @RequestParam(value = "hugoSymbol") String hugoSymbol
        , @ApiParam(value = "Variant name") @RequestParam(value = "variant") String variant
    );

    @ApiOperation(value = "", notes = "Get gene related numbers", response = GeneNumber.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/numbers/gene/{hugoSymbol}",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<GeneNumber> utilsNumbersGeneGet(
        @ApiParam(value = "The gene symbol used in Human Genome Organisation.", required = true) @PathVariable("hugoSymbol") String hugoSymbol
    );

    @ApiOperation(value = "", notes = "Get gene related numbers of all genes. This is for main page word cloud.", response = GeneNumber.class, responseContainer = "Set")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/numbers/genes/",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<Set<GeneNumber>> utilsNumbersGenesGet();

    @ApiOperation(value = "", notes = "Get numbers served for the main page dashboard.", response = MainNumber.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/numbers/main/",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<MainNumber> utilsNumbersMainGet();

    @ApiOperation(value = "", notes = "Get gene related numbers of all genes. This is for main page word cloud.", response = LevelNumber.class, responseContainer = "Set")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/numbers/levels/",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<Set<LevelNumber>> utilsNumbersLevelsGet();

    @ApiOperation(value = "", notes = "Check if clinical trials are valid or not by nctId.", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/validation/trials",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Map<String, Boolean>> validateTrials(@ApiParam(value = "NCT ID list") @RequestParam(value = "nctIds") List<String> nctIds) throws ParserConfigurationException, SAXException, IOException;

    @ApiOperation(value = "", notes = "Check if the genomic example will be mapped to OncoKB variant.", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/match/variant",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<Map<String, Boolean>> validateVariantExampleGet(
        @ApiParam(value = "Gene Hugo Symbol") @RequestParam(value = "hugoSymbol") String hugoSymbol
        , @ApiParam(value = "The OncoKB variant") @RequestParam(value = "variant") String variant
        , @ApiParam(value = "The genomic examples.") @RequestParam(value = "examples") String examples
    ) throws ParserConfigurationException, SAXException, IOException;

    @ApiOperation(value = "", notes = "Check which OncoKB variants can be mapped on genomic examples.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/match/variant",
        consumes = {"application/json"},
        produces = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<List<MatchVariantResult>> validateVariantExamplePost(@ApiParam(value = "List of queries. Please see swagger.json for request body format.", required = true) @RequestBody(required = true) MatchVariantRequest body
    );

    @ApiOperation(value = "", notes = "Get the full list of OncoTree Maintype.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/oncotree/mainTypes",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<String>> utilsOncoTreeMainTypesGet();

    @ApiOperation(value = "", notes = "Get the full list of OncoTree Subtypes.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/utils/oncotree/subtypes",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<TumorType>> utilsOncoTreeSubtypesGet();

    @ApiOperation(value = "", notes = "Get the list of evidences by levels.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
//    @RequestMapping(value = "/utils/evidences/levels",
//        produces = {"application/json"},
//        method = RequestMethod.GET)
    ResponseEntity<Map<LevelOfEvidence, Set<Evidence>>> utilsEvidencesByLevelsGet();

    @ApiOperation(value = "", notes = "Get the list of relevant tumor types.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = List.class)})
    @RequestMapping(value = "/utils/relevantTumorTypes",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<TumorType>> utilRelevantTumorTypesGet(
        @ApiParam(value = "OncoTree tumor type name/main type/code") @RequestParam(value = "tumorType") String tumorType
    );

    @ApiOperation(value = "", notes = "Get all the info for the query", response = VariantAnnotation.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = VariantAnnotation.class)})
//    @RequestMapping(value = "/utils/variantAnnotation",
//        produces = {"application/json"},
//        method = RequestMethod.GET)
    ResponseEntity<VariantAnnotation> utilVariantAnnotationGet(
        @ApiParam(value = "hugoSymbol") @RequestParam(value = "hugoSymbol", required = false) String hugoSymbol
        , @ApiParam(value = "entrezGeneId") @RequestParam(value = "entrezGeneId", required = false) Integer entrezGeneId
        , @ApiParam(value = "Alteration") @RequestParam(value = "alteration", required = false) String alteration
        , @ApiParam(value = "OncoTree tumor type name/main type/code") @RequestParam(value = "tumorType", required = false) String tumorType
    );
}

