package JFX.mote;

import JFX.mote.layout.Panel;

public class Frame {
	public Frame(String title) {
		super();
		App.title = title;
		App.setup();
	}
	public Frame(String title,int Width,int Height) {
		this(title);
		App.height = Height;
		App.width = Width;
	}
	/**
	 * Add the node in the App.
	 * @param node
	 */
	public void add(Component node) {
		App.content.add(node);
	}
	/**
	 * Show and Start the App.
	 */
	public void show() {
		String[] args = {};
		App.launch(App.class,args);
	}
	/**
	 * get the Tile of App 
	 * @return title
	 */
	public String getTitle() {
		return App.title;
	}
	/**
	 * Get the App's Panel
	 * @return Panel
	 */
	public Panel<?> getPane() {
		return App.content;
	}
	/**
	 * Set the Panel of App 
	 * @param layout
	 */
	public void setPanel(Panel<?> layout) {
		App.setPanel(layout);
	}
}
