package com.notes.a.jackson.configuration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.notes.a.entity.Notes;

/**
 * https://www.baeldung.com/jackson-deserialization
 * Getting Started with Deserialization in Jackson | Baeldung
 * @author sabaja
 *
 */
public class NotesJacksonDeserializer /*extends JsonDeserializer<Notes>*/{
//
//	@Override
//	public Notes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//		ObjectCodec oc = jsonParser.getCodec();
//        JsonNode node = oc.readTree(jsonParser);
//		return new Notes(
//				node.get("title").asText(),
//				node.get("content").asText(),
//				node.get("user").get("userName").asText(),
//	}

}
