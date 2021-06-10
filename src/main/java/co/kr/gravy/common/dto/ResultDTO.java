package co.kr.gravy.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultDTO {

	private String systemId;
	private String resultCode;
	private String resultMsg;
	
	public String toStringDefault() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
    public String toStringJson() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }    
    public String toStringMultiline() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    public String toStringNoClass() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }    
    public String toStringNoFieldName() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
    }
    public String toStringShortPrefix() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }    
    public String toStringSimple() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}