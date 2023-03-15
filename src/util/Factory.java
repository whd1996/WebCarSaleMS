package util;

public class Factory {
	@SuppressWarnings("unchecked")
	public static Object getInstance(String type) {
		Object obj = null;
		String className = JDBCUtils.getObject(type);
		
		try {
			Class c = Class.forName(className);
			obj = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
