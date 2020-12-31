package JFX.mote.layout;

import java.util.ArrayList;
import java.util.List;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Element;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

public class Table extends Component{
	GridPane table;
	List<List<Element>> content = new ArrayList<List<Element>>();
	protected int row;
	protected int col;
	public Table() {
		super();
		table = new GridPane();
		add(table);
	}
	@Override
	public void init() {
		loaded = true;
		update();
	}
	static int fontSize = 16; 
	public void update() {	
		if(loaded) {
			fontSize = width==0?App.width:width;
			int nrow = content.size();
			fontSize/=nrow+2;				
			table.getChildren().clear();
			row = 0;
			content.forEach(line->{	
				col = 0;
				line.forEach(cell->{
					((Component) cell).setFontSize(8);
					cell.init();
					((Component) cell).setSize(fontSize,fontSize);
					table.add(((Component) cell).toNode(), col++, row);
				});
				row++;
			});
		}
	}
	public void addRow(int y,ListLine n) {
		List<Element> f = new ArrayList<>();
		f.add(n);
		content.add(y,f);
	}
	public void addColumn(int y,ListLine n) {
		if(y<content.size()) {
			List<Element> row = content.get(y);
			row.add(n);
		}else {
			addRow(y, n);
		}
		//update();
	}
	@Override
	protected void updateStyle() {
		table.setBackground(new Background(new BackgroundFill(backgroundColor,null,null)));
		setAlignment(Pos.BOTTOM_RIGHT);
		table.setMinSize(width, height);
		table.setMaxSize(width, height);
		setMaxSize(width, height);
	}
	public void clearColumn(int y) {
		if(y<content.size()) {
			List<Element> row = content.get(y);
			row.clear();
		}
	}
}
