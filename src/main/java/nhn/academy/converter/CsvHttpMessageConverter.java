package nhn.academy.converter;

import nhn.academy.model.Member;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        // 컨트롤러에서 리턴한 값의 clazz를 csv,json 등등으로 "데이터"로 만들 수 있어??
        return super.canWrite(clazz, mediaType);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //TODO Not Supported Type
        return null;
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false;
    }

    @Override
    protected void writeInternal(Member member
            , HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            writer.append("name").append(",").append("age").append(",").append("clazz").append(System.lineSeparator());
            writer.write(member.getName());
            writer.write(",");
            writer.write(String.valueOf(member.getAge()));
            writer.write(",");
            writer.write(String.valueOf(member.getClazz()));
            writer.flush();
        }

        return;
    }


}
