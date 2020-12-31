package JFX.mote.layout;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Element;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Panel<T extends Pane> extends Component implements Element{
	protected T layout;
	private java.util.List<Element> childs = new ArrayList<Element>();
	protected String nextpage;
	private Consumer<? super Element> display;
	
	
	public Panel(T t,String name) {
		this(t);
		PanelManager.put(name,this);
	}

	public Panel(T t) {
		layout = t;
		if(t instanceof BorderPane) {
			display = x->{
				adderInPos(x);
			};
		}else {
			display = x->{adder(x);};			
		}
		super.getChildren().add(layout);
	}

	@SuppressWarnings("unchecked")
	public Panel(String name) {
		this((T) new StackPane(),name);
	}
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		childs.clear();
		super.getChildren().clear();
	}
	public void init() {
		childs.forEach(display);
	}
	protected void adder(Element x) {
		if(x instanceof Component){
			((Component) x).heredite(this);
			((Component) x).toNode();
			layout.getChildren().add((Node) x);
		}
	}
	protected void adderInPos(Element x) {
		if(x instanceof Component){
			((Component) x).heredite(this);
			((Component) x).toNode();
			Node node = (Node) x;
			BorderPane parent = (BorderPane)layout;
			switch(((Component) x).position) {
				case Top:parent.setTop(node);break;
				case Left:parent.setLeft(node);break;
				case Bottom:parent.setBottom(node);break;
				case Rigth:parent.setRight(node);break;
				case Center:parent.setCenter(node);break;
			}
		}
	}
	/**
	 * Set the size of this Panel
	 *
	 * @param width
	 * @param height
	 */
	public void setMinSize(int width, int height) {
		layout.setMinSize(width, height);
	}
	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}
	 /** Append Component to this Panel
	 * @param nodes
	 */
	public void add(Component ...nodes) {
		if(App.loaded) {	
			Stream.of(nodes).forEach(display);	
		}else {
			Stream.of(nodes).forEach(childs::add);			
		}
	}
	/**
	 * Append Panel to this Panel
	 * @param nodes
	 */
	public void add(Panel<?> ...nodes) {
		if(App.loaded) {	
			Stream.of(nodes).forEach(display);	
		}else {
			Stream.of(nodes).forEach(childs::add);			
		}	
	}
	@SuppressWarnings("unchecked")
	protected  void openLayout(String page) {
		try {
			this.getChildren().clear();
			PanelManager.get(page).init();
			this.getChildren().addAll(PanelManager.get(page).getChildren());		
		}catch(Exception e){
			App.setPanel(PanelManager.get(page));			
		}
	}
	/**
	 * Set the next Panel, when the use of this Panel is end.
	 * @param name
	 */
	public void setNext(String name) {
		this.nextpage = name;
	}
	public void next() {
		this.openLayout(nextpage);
	}
	
	private static class EmptyLayout extends Panel<StackPane>{
		
		public EmptyLayout(String name) {
			super(new StackPane(), name);
		}

		@Override
		protected void updateStyle() {}
	}
	/**
	 * Create instance of Panel by name.
	 * @param name
	 * @return
	 */
	public static Panel<?> instance(String name) {
		return new EmptyLayout(name);
	}
	@Override
	protected void updateStyle() {
		setBackground(new Background(new BackgroundFill(backgroundColor,null,null)));
		if(width!=0 && height!=0)
			setMaxSize(width, height);
	}
	protected void add(Node ...n) {
		layout.getChildren().addAll(n);
	}

	protected void of(BorderPane borderPane) {
		
	}
	
}
