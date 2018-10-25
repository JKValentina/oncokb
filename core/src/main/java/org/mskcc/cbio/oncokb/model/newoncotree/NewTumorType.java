/*
 * Copyright (c) 2016-2018 Memorial Sloan-Kettering Cancer Center.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 * documentation provided hereunder is on an "as is" basis, and
 * Memorial Sloan-Kettering Cancer Center
 * has no obligations to provide maintenance, support,
 * updates, enhancements or modifications.  In no event shall
 * Memorial Sloan-Kettering Cancer Center
 * be liable to any party for direct, indirect, special,
 * incidental or consequential damages, including lost profits, arising
 * out of the use of this software and its documentation, even if
 * Memorial Sloan-Kettering Cancer Center
 * has been advised of the possibility of such damage.
*/
package org.mskcc.cbio.oncokb.model.newoncotree;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-04-04T17:16:11.368Z")
public class NewTumorType {

    private String code = null;
    private String color = null;
    private String name = null;
    private String mainType = null;
    private Map<String, List<String>> externalReferences = new HashMap<String, List<String>>();
    private String tissue = null;
    private Map<String, NewTumorType> children = new HashMap<String, NewTumorType>();
    private String parent = null;
    private List<String> history = new ArrayList<String>();
    private Integer level = null;
    private static final String UNKNOWN_ONCOTREE_NODE_LEVEL = "-1";

    public NewTumorType() {}

    public NewTumorType(NewTumorType otherTumorType) {
        this.code = otherTumorType.code;
        this.color = otherTumorType.color;
        this.name = otherTumorType.name;
        this.mainType = otherTumorType.mainType;
        // shallow copy
        this.externalReferences = new HashMap<String, List<String>>(otherTumorType.externalReferences);
        this.tissue = otherTumorType.tissue;
        // shallow copy
        this.children = new HashMap<String, NewTumorType>(otherTumorType.children);
        this.parent = otherTumorType.parent;
        this.history = new ArrayList<String>(otherTumorType.history);
        this.level = otherTumorType.level;
    }

    public NewTumorType deepCopy() {
        NewTumorType newTumorType = new NewTumorType();
        newTumorType.code = this.code;
        newTumorType.color = this.color;
        newTumorType.name = this.name;
        newTumorType.mainType = this.mainType;
        newTumorType.externalReferences = new HashMap<String, List<String>>(this.externalReferences);
        newTumorType.tissue = this.tissue;
        newTumorType.children = new HashMap<String, NewTumorType>(this.children.size());
        for (Map.Entry<String, NewTumorType> entry : this.children.entrySet()) {
            newTumorType.children.put(entry.getKey(), entry.getValue().deepCopy());
        }
        newTumorType.parent = this.parent;
        newTumorType.history = new ArrayList<String>(this.history);
        newTumorType.level = this.level;
        return newTumorType;
    }

    /**
     * Unique identifier representing OncoTree tumor types.
     **/
    @ApiModelProperty(value = "Unique identifier representing OncoTree tumor types.")
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Tumor type color.
     **/
    @ApiModelProperty(value = "Tumor type color.")
    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    /**
     * Tumor type name.
     **/
    @ApiModelProperty(value = "Tumor type name.")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("mainType")
    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }


    /**
     * External references (e.g. NCI and UMLS)
     **/
    @ApiModelProperty(value = "External references e.g. NCI Thesaurus or UMLS code(s).")
    @JsonProperty("externalReferences")
    public Map<String, List<String>> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(Map<String, List<String>> externalReferences) {
        this.externalReferences = externalReferences;
    }

    public void setExternalReference(String type, List<String> codes) {
        if (codes == null) {
            this.externalReferences.put(type, new ArrayList<String>());
        } else {
            this.externalReferences.put(type, codes);
        }
    }

    public void addExternalReference(String type, String code) {
        if (!this.externalReferences.containsKey(type)) {
            this.externalReferences.put(type, new ArrayList<String>());
        }
        this.externalReferences.get(type).add(code);
    }

    /**
     * The tissue this tumor type belongs to.
     **/
    @ApiModelProperty(value = "The tissue this tumor type belongs to.")
    @JsonProperty("tissue")
    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }


    /**
     * List of all available children tumor types.
     **/
    @ApiModelProperty(value = "List of all available children tumor types.")
    @JsonProperty("children")
    public Map<String, NewTumorType> getChildren() {
        return children;
    }

    public void setChildren(Map<String, NewTumorType> children) {
        if (children == null) {
            this.children = new HashMap<String, NewTumorType>();
        } else {
            this.children = children;
        }
    }

    public void addChild(NewTumorType child) {
        if (children == null) {
            this.children = new HashMap<String, NewTumorType>();
        }
        this.children.put(child.getCode(), child);
    }

    /**
     * The parent node code.
     **/
    @ApiModelProperty(value = "The parent node code.")
    @JsonProperty("parent")
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("history")
    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    /**
     **/
    @ApiModelProperty(value = UNKNOWN_ONCOTREE_NODE_LEVEL)
    @JsonProperty("level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewTumorType tumorType = (NewTumorType) o;
        return Objects.equals(code, tumorType.code) &&
            Objects.equals(color, tumorType.color) &&
            Objects.equals(name, tumorType.name) &&
            Objects.equals(mainType, tumorType.mainType) &&
            Objects.equals(externalReferences, tumorType.externalReferences) &&
            Objects.equals(tissue, tumorType.tissue) &&
            Objects.equals(children, tumorType.children) &&
            Objects.equals(parent, tumorType.parent) &&
            Objects.equals(history, tumorType.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, color, name, mainType, externalReferences, tissue, children, parent, history);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TumorType {\n");
        sb.append("  code: ").append(code).append("\n");
        sb.append("  color: ").append(color).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  mainType: ").append(mainType).append("\n");
        for (String type : externalReferences.keySet()) {
            sb.append("  ").append(type).append(": ").append(StringUtils.join(externalReferences.get(type), ",")).append("\n");
        }
        sb.append("  tissue: ").append(tissue).append("\n");
        sb.append("  children: ").append(children).append("\n");
        sb.append("  parent: ").append(parent).append("\n");
        sb.append("  history: ").append(history).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
