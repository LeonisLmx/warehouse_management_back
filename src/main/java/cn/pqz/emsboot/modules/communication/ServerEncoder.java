package cn.pqz.emsboot.modules.communication;

import cn.pqz.emsboot.modules.communication.entity.Message;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;


/**
 * Encoder which encodes the object data into messages
 * which can be transported over the websocket connection.
 */

public class ServerEncoder implements Encoder.Text<Message> {

        /**
         * Encode the instance of MyMessage into a JSON string.
         */
    @Override
    public String encode(Message msg) throws EncodeException {
        StringWriter writer = new StringWriter();
        //Makes use of the JSON Streaming API to build the JSON string.
//        JSON.createGenerator(writer)
//                .writeStartObject()
//                .write("message", myMsg.message)
//                .write("time", myMsg.receivedAt.toString())
//                .writeEnd()
//                .flush();
        ObjectMapper om =new ObjectMapper();
        try {
            writer.write(om.writeValueAsString(msg));
            System.out.println(writer.toString());
            writer.flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }

}
