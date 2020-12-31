package JFX.mote.layout;

import java.util.HashMap;

@SuppressWarnings("rawtypes")
public abstract class PanelManager  {
	private static HashMap<String, Panel> I; 
	private static boolean loaded;
	protected static void put(String id,Panel layout) {
		if(!loaded) {
			init();
		}
		I.put(id, layout);
	};
	static void init(){
		loaded = true;
		I = new HashMap<String,Panel>();
	}
	public static Panel get(String page) {
		Panel layout = I.get(page);
		if(layout==null) {
			System.out.println("No Page Found named "+page);
			return Panel.instance(page);
		}else {
			return layout;			
		}
	}
}
