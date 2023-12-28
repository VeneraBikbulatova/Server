package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Objects;

@JsonDeserialize
public class CommonResponse <T> {
    private boolean ok;
    private int errorCode;
    private String errorMessage;
    private List<String> errors;
    private T data;

    public CommonResponse(){}

//    @ConstructorProperties({"ok", "errorCode", "errorMessage", "errors", "data"})
    @JsonCreator
    public CommonResponse(@JsonProperty("data") T data) {
        ok = true;
        this.data = data;
        errorCode = 0;
        errorMessage = null;
        errors = null;
    }

    public CommonResponse(int errorCode, String errorMessage, List<String> errors) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errors = errors;
        ok = false;
        data = null;
    }

    public boolean ok() {
        return ok;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonResponse<?> that = (CommonResponse<?>) o;
        return ok == that.ok && errorCode == that.errorCode && Objects.equals(errorMessage, that.errorMessage) && Objects.equals(errors, that.errors) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ok, errorCode, errorMessage, errors, data);
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "ok=" + ok +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", errors=" + errors +
                ", data=" + data +
                '}';
    }
}
