package challenge.api.v1.model.message;

public enum Type {
	text,
	image,
	video;
	
	public static String CONVERSION_ERROR = "Invalid Type";
	
	public static Type getType(String type) throws Exception{
		switch (type){
			case "text":
				return text;
			case "image":
				return image;
			case "video":
				return video;
			default:
				throw new Exception(CONVERSION_ERROR);
		}
	}
}
