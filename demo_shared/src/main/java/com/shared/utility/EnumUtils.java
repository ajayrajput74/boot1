package com.shared.utility;

public class EnumUtils {

	public enum MessageType {
		SUCCESS, INFO, WARNING, ERROR
	}

	public enum Claims {

		PERMISSION("permission"), USERNAME("username"), USER_ID("userId"), PRPFILEIMAGE("profileImageUrl"), APISTATUS(
				"apiStatus"), FULLNAME("fullName"), TIMEZONE("timeZone"), ROLEID("roleId"), CLIENTID("clientId"), PRPFILEIMAGETHUMBNAIL("profileImageThumbnailUrl");

		public final String value;

		private Claims(String value) {
			this.value = value;
		}
	}
	public enum DocumentType {
		DOCUMENT("Document"), IMAGE("Image");
		
		public String type;


		private DocumentType(String type) {
			this.type = type;
		}
		
	}
	
	public enum Gateway {
		// name
		GATEWAY_NAME_IDP("IDP"),
		// GATEWAY_NAME_VISTAR("vistar"),
		// GATEWAY_NAME_GLOBALWAVE("globalwave"),
		GATEWAY_NAME_CELLULAR("cellularUDP"), GATEWAY_NAME_CELLULAR_TCP("CellularTCP");

		private String name;

		private Gateway(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
	
}
