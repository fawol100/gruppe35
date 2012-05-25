package Character;

import main.Menu;
import Bomb.Bomb;
import Level.Levellist;

/** Character sind Bomberman, Gegner usw. **/
public class Character {
	protected String name;
	protected double speed;
	protected double spawnx;
	protected double spawny;
	protected int pixsizex = 25;
	protected int pixsizey = 40;
	protected double posx;
	protected double posy;
	protected int maxbombs;
	protected int bombs = 0;
	protected int bombrange;
	protected int bombtimer;
	protected int lifes;

	public int getPixsizex() {
		return pixsizex;
	}

	public void setPixsizex(int pixsizex) {
		this.pixsizex = pixsizex;
	}

	public int getPixsizey() {
		return pixsizey;
	}

	public void setPixsizey(int pixsizey) {
		this.pixsizey = pixsizey;
	}

	public Character(String name, double spawnx, double spawny, double speed,
			int maxbombs, int bombrange, int bombtimer, int lifes) {
		this.name = name;
		this.spawnx = spawnx;
		this.spawny = spawny;
		this.speed = speed;
		this.maxbombs = maxbombs;
		this.bombrange = bombrange;
		this.bombtimer = bombtimer;
		this.lifes = lifes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPosx() {
		return posx;
	}

	public int getDrawx() {
		return ((int) (posx * 50 - pixsizex * 0.5));
	}

	public int getDrawy() {
		return ((int) (posy * 50 - pixsizey * 0.5));
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public int getMaxbombs() {
		return maxbombs;
	}

	public void setMaxbombs(int maxbombs) {
		this.maxbombs = maxbombs;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getBombrange() {
		return bombrange;
	}

	public void setBombrange(int bombrange) {
		this.bombrange = bombrange;
	}

	public int getBombtimer() {
		return bombtimer;
	}

	public void setBombtimer(int bombtimer) {
		this.bombtimer = bombtimer;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public void placebomb() {
		if ((bombs < maxbombs)
				&& (Levellist.activeLevel.getField((int) (posx), (int) (posy))
						.getBomb() == null)) {
			bombs++;
			Bomb bomb = new Bomb(Levellist.activeLevel, (int) (posx),
					(int) (posy), this, bombtimer, bombrange);
			bomb.start();
		}
	}

	public void kill() {
		System.out.println(this.name + " dies!");
		lifes--;
		// Verlasse Feld
		Levellist.activeLevel.getField((int) (posx), (int) (posy)).leave(this);
		if (lifes <= 0) {
			System.out.println("Game over for " + this.name);
			Menu.panelvisible = false;
			Menu.feld.initialize();
		} else {
			spawn();
		}
	}

	public void move(int dirx, int diry) {
		int oldx = (int) (posx);
		int oldy = (int) (posy);
		double newposx = (posx) + speed * dirx;
		double newposy = (posy) + speed * diry;
		int newx = (int) (newposx);
		int newy = (int) (newposy);
		// wird der Rand des Spielfeldes nicht verlassen?
		if ((newposx > 0.0) && (newposx < Levellist.activeLevel.getXsize())
				&& (newposy > 0.0)
				&& (newposy < Levellist.activeLevel.getYsize())) {
			// W�rde ein neues Feld betreten?
			if (((newx - oldx) != 0) || ((newy - oldy) != 0)) {
				// Kann dieses Feld �berhaupt betreten werden?
				if (Levellist.activeLevel.getField(newx, newy).enter(this)) {
					// Kann betreten werden
					// Gehe weiter
					posx = newposx;
					posy = newposy;
					// Verlasse altes Feld
					Levellist.activeLevel.getField(oldx, oldy).leave(this);
				} else {
					// Kann nicht betreten werden
				}

			} else {
				// Kein neues Feld wird betreten, gehe einfach weiter
				posx = newposx;
				posy = newposy;
			}
		}

	}

	public void spawn() {
		posx = Levellist.activeLevel.getSpawnx();
		posy = Levellist.activeLevel.getSpawny();
		System.out.println(name + " spawns at " + posx + "," + posy);
		if (!Levellist.activeLevel.getField((int) (posx), (int) (posy)).enter(
				this)) {
			System.out.println("Invalid Spawn point for " + name);
		}
	}
}
