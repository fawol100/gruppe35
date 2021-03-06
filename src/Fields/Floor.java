package Fields;

/**
 * @author Peet
 * 
 *         The Floor class is a child of the Field class and is the typical
 *         passable Field which does not hinder Characters or flames. It also
 *         does not change when a bomb explodes in its vicinity
 */

public class Floor extends Field {

	// Kopiere dieses Feld
	public Floor copy() {
		return new Floor(this);
	}

	// Copy Constructor
	public Floor(Floor floor) {
		super(floor);
	}

	// Default Constructor
	public Floor() {
		this.solid = false;
		this.transformto = null;
	}
}
