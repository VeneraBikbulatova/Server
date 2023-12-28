package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.beans.ConstructorProperties;
import java.util.Objects;

@JsonDeserialize
public class ResponseEntity<T>{
    private T body;
    private int httpcode;

    public ResponseEntity(){}

//    @ConstructorProperties({"body", "httpcode"})
    @JsonCreator
    public ResponseEntity(@JsonProperty("body") T body, @JsonProperty("httpcode") int httpcode) {
        this.body = body;
        this.httpcode = httpcode;
    }

    public T getBody() {
        return body;
    }

    public int getHttpcode() {
        return httpcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEntity<?> that = (ResponseEntity<?>) o;
        return httpcode == that.httpcode && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, httpcode);
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "body=" + body +
                ", httpcode=" + httpcode +
                '}';
    }


}
