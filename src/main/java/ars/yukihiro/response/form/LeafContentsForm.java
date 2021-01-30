package ars.yukihiro.response.form;

import javax.validation.constraints.NotNull;

public class LeafContentsForm extends AbstractNodeForm {

    private static final long serialVersionUID = 1L;
    @NotNull
    private String contentsVal;
    private Long contentsVersion;

    public String getContentsVal() { return contentsVal; }

    public void setContentsVal(String contentsVal) {
        this.contentsVal = contentsVal;
    }

    public Long getContentsVersion() { return contentsVersion; }

    public void setContentsVersion(Long contentsVersion) { this.contentsVersion = contentsVersion; }
}
