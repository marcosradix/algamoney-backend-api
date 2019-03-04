/*package br.com.workmade.algamoneybackendapi.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@Configuration
public class CustomDateSerializer extends StdSerializer<Date> {
	private static final long serialVersionUID = 865372258769352204L;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public CustomDateSerializer() {
		this(null);
	}

	public CustomDateSerializer(Class<Date> t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(formatter.format(value));
		
	}
}
*/