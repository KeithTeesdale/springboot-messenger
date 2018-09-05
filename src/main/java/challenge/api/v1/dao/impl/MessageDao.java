package challenge.api.v1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import challenge.api.v1.dao.BaseDao;
import challenge.api.v1.model.message.Content;
import challenge.api.v1.model.message.Message;
import challenge.api.v1.model.message.Type;
import challenge.api.v1.model.message.request.SendMessageRequest;
import challenge.api.v1.model.message.response.GetMessageResponse;
import challenge.api.v1.model.message.response.SendMessageResponse;

public class MessageDao extends BaseDao {
	private final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final String MESSAGE_ID = "id";
	private final String MESSAGE_SENT_AT = "sent_at";
	private final String MESSAGE_SEND_ID = "send_uid";
	private final String MESSAGE_RECEIVE_ID = "receive_uid";
	private final String MESSAGE_CONTENT_TYPE = "content_type";
	private final String MESSAGE_CONTENT_TEXT = "content_text";
	private final String CREATE_MESSAGE_SQL = "INSERT into Message (send_uid, receive_uid, content_type, content_text) values (?, ?, ?, ?);";
	private final String GET_MESSAGE_BY_ID_SQL = "SELECT id, sent_at from Message where id=?";
	private final String GET_MESSAGES_SQL = "SELECT id, sent_at, send_uid, receive_uid, content_type, content_text from Message where receive_uid=? and id >= ? order by id ASC LIMIT ?";


	public MessageDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	public SendMessageResponse saveMessage(SendMessageRequest messageRequest){
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    		    new PreparedStatementCreator() {
    		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
    		            PreparedStatement ps = connection.prepareStatement(CREATE_MESSAGE_SQL, new String[]{MESSAGE_ID});
    		            ps.setLong(1, messageRequest.getSender());
    		            ps.setLong(2, messageRequest.getRecipient());
    		            ps.setString(3, messageRequest.getContent().getType().name());
    		            ps.setString(4, messageRequest.getContent().getText());
    		            return ps;
    		        }
    		    },
    		    keyHolder);
    	
		//A procedure would have been useful here. 
    	//Output parameters could avoid a second DB call and improve performance
    	return getMessageById(keyHolder.getKey().longValue());
	}
	
	public SendMessageResponse getMessageById(long messageId){
		//Should use a RowMapper here
		Map<String, Object> result =  jdbcTemplate.queryForMap(GET_MESSAGE_BY_ID_SQL, new Object[]{messageId});
		Number id = (Number)result.get(MESSAGE_ID);
		LocalDateTime sentAt = LocalDateTime.parse((String)result.get(MESSAGE_SENT_AT), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		
		SendMessageResponse response = new SendMessageResponse();
		response.setId(id.longValue());
		response.setTimestamp(sentAt);
		
		return response;
	}
	
	public GetMessageResponse getMessage(long recipientId, int startId, Integer... limitOptional) throws Exception{
		int limit = 100;
		if(limitOptional != null && limitOptional[0] != null && limitOptional[0] > 0){
			limit = limitOptional[0];
		}
		
		//Should use a RowMapper here
		List<Map<String, Object>> results = jdbcTemplate.queryForList(GET_MESSAGES_SQL, new Object[]{recipientId, startId, limit});
		GetMessageResponse response = new GetMessageResponse();
		response.setMessages(new ArrayList<Message>());
		
		for(Map<String, Object> map : results){
			Message msg = new Message();
			msg.setId(((Number)map.get(MESSAGE_ID)).longValue());
			msg.setRecipient(((Number)map.get(MESSAGE_RECEIVE_ID)).longValue());
			msg.setSender(((Number)map.get(MESSAGE_SEND_ID)).longValue());
			msg.setTimestamp(LocalDateTime.parse((String)map.get(MESSAGE_SENT_AT), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
			
			Content content = new Content();
			content.setText((String)map.get(MESSAGE_CONTENT_TEXT));
			content.setType(Type.getType(((String)map.get(MESSAGE_CONTENT_TYPE))));
			msg.setContent(content);
			
			response.getMessages().add(msg);
		}
		
		
		return response;
	}
}
