package challenge;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.api.v1.dao.impl.MessageDao;
import challenge.api.v1.dao.impl.TokenDao;
import challenge.api.v1.dao.impl.UserDao;
import challenge.api.v1.dao.impl.UserTokenDao;
import challenge.api.v1.model.message.request.SendMessageRequest;
import challenge.api.v1.model.message.response.GetMessageResponse;
import challenge.api.v1.model.message.response.SendMessageResponse;
import challenge.api.v1.model.user.request.UserRequest;
import challenge.api.v1.model.user.response.CreateUserResponse;
import challenge.api.v1.model.user.response.LoginUserResponse;

@RestController
public class Controller {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/check", method = { RequestMethod.POST })
    public Map<String, String> check() {
        int result = this.jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        if (result != 1) {
            throw new RuntimeException("Unexpected query result");
        }
        return Collections.singletonMap("health", "ok");
    }
    
    @RequestMapping(value = "/users", method = { RequestMethod.POST })
    public ResponseEntity<CreateUserResponse> userCreate(@RequestBody UserRequest userRequest) {
    	try {
    		UserDao dao = new UserDao(this.jdbcTemplate);
            return ResponseEntity.accepted().body(dao.userCreate(userRequest));    		
    	}catch(Exception e) {//Exception handling should be more granular. For example, SQLException, 
    		e.printStackTrace();//Needs a logging implementation like log4j2 - *NOT* how I'd do this in PROD! :)
    		CreateUserResponse error = new CreateUserResponse();
    		error.setId(-1);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    	}
    }
    
    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public ResponseEntity<LoginUserResponse> userLogin(@RequestBody UserRequest userRequest) {
    	try {
    		UserTokenDao utDao = new UserTokenDao(this.jdbcTemplate);
    		LoginUserResponse response = utDao.createUserToken(userRequest);
    		return ResponseEntity.accepted().body(response);   		
    	}catch(Exception e) {//Exception handling should be more granular.
    		e.printStackTrace();//log4j2
    		LoginUserResponse error = new LoginUserResponse();
    		error.setId(-1);
    		error.setToken("invalid");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    	}
    }
    
    @RequestMapping(value = "/messages", method = { RequestMethod.POST })
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestHeader(value="Authorization") String token, @RequestBody SendMessageRequest messageRequest) {
    	try{ 
    		if(!isValidToken(token))
    			throw new IllegalArgumentException("No Valid Token Found for: "+messageRequest.getSender());
    		
    		MessageDao mdao = new MessageDao(this.jdbcTemplate);
    		SendMessageResponse response = mdao.saveMessage(messageRequest);
    		return ResponseEntity.accepted().body(response);  
    	}catch(Exception e){//Exception handling should be more granular.
    		e.printStackTrace();//log4j2
    		SendMessageResponse error = new SendMessageResponse();
    		error.setId(-1);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    	}
    }
    
    @RequestMapping(value = "/messages", method = { RequestMethod.GET })
    public ResponseEntity<GetMessageResponse> getMessage(
    		@RequestHeader(value="Authorization") String token, 
    		@RequestParam("recipient") long recipientId, 
    		@RequestParam("start") int msgStartId,
    		@RequestParam(value = "limit", required = false) Integer msgLimit){
    	
    	try{
    		if(!isValidToken(token))
    			throw new IllegalArgumentException("No Valid Token Found");
    		
    		MessageDao mdao = new MessageDao(this.jdbcTemplate);
    		GetMessageResponse response = mdao.getMessage(recipientId, msgStartId, msgLimit);
    		return ResponseEntity.accepted().body(response); 
    	}catch(Exception e){//Exception handling should be more granular.
    		e.printStackTrace();//log4j2
    		GetMessageResponse error = new GetMessageResponse();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    	}
    }
    
    private boolean isValidToken(String token){
    	return (new TokenDao(this.jdbcTemplate)).isValidToken(token);
    }
    
}