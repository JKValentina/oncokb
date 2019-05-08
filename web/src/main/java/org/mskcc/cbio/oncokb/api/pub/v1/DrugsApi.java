package org.mskcc.cbio.oncokb.api.pub.v1;

import io.swagger.annotations.*;
import org.mskcc.cbio.oncokb.model.Drug;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-14T18:47:53.991Z")

@Api(tags = "Drugs", description = "OncoKB Drugs")
public interface DrugsApi {

    @ApiOperation(value = "", notes = "Get all curated drugs.", response = Drug.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Drug.class, responseContainer = "List")})
//    @RequestMapping(value = "/drugs",
//        produces = {"application/json"},
//        method = RequestMethod.GET)
    ResponseEntity<List<Drug>> drugsGet();


    @ApiOperation(value = "", notes = "Search drugs.", response = Drug.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Drug.class, responseContainer = "List")})
//    @RequestMapping(value = "/drugs/lookup",
//        produces = {"application/json"},
//        method = RequestMethod.GET)
    ResponseEntity<List<Drug>> drugsLookupGet(
        @ApiParam(value = "Drug Name") @RequestParam(value = "name", required = false) String name
        , @ApiParam(value = "NCI Thesaurus Code") @RequestParam(value = "ncitCode", required = false) String ncitCode
        , @ApiParam(value = "Drug Synonyms") @RequestParam(value = "synonym", required = false) String synonym
        , @ApiParam(value = "Exactly Match", required = true) @RequestParam(value = "exactMatch", required = true, defaultValue = "true") Boolean exactMatch
    );

}
