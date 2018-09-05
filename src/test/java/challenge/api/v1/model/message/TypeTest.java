package challenge.api.v1.model.message;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeTest {

	@Test
	public void testGetTypeValid() {
		try{
			assertEquals(Type.image, Type.getType("image"));
			assertEquals(Type.text, Type.getType("text"));
			assertEquals(Type.video, Type.getType("video"));
		}catch(Exception e){
			fail("Invalid Type");
		}
	}

	@Test
	public void testGetTypeInvalid() {
		try{
			Type.getType("not image, text or video");
		}catch(Exception e){
			assertTrue(e.getLocalizedMessage().equals(Type.CONVERSION_ERROR));
		}
	}
}
