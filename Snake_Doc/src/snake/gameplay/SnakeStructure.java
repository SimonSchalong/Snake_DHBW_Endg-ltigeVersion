package snake.gameplay;

import java.util.Iterator;

import snake.Game;

/*
 * Linked List of Coordinates representing the snake
 */
public class SnakeStructure implements Iterable<Coordinate>{
	
	private BodyNode head;
	
	public SnakeStructure(int startX, int startY) {
		this(new Coordinate(startX, startY));
	}
	public SnakeStructure(Coordinate startCoord) {
		head = new BodyNode(startCoord);
	}
	/**
	 * @param newDirection is the new direction is determined by the player via the arrow keys
	 */
	public void setDirection(Direction newDirection) {
		head.direction = newDirection;
	}
	private int queriedLengthIncrease = 0;
	public void increaseLength(int i) {
		queriedLengthIncrease += i;
	}
	/**
	 * moves the snake forward and increases the lengh if neccessary
	 */
	public void moveForward() {
		if (queriedLengthIncrease > 0) {			//snake has eaten and instead of moving the body a new head will be added which implies the snake growing
			Coordinate c = head.coordinate.clone();
			c.x += head.direction.getDirectionX();
			c.y += head.direction.getDirectionY();
			shiftCoordinateIfNoWallCollsion(c);
			BodyNode newHead = new BodyNode(c, head, head.direction);
			head = newHead;
			queriedLengthIncrease--;
		}else {
			Direction parentDir = head.direction;	//moving the snake body
			BodyNode node = head;
			boolean b;
			do {
				//Body segment moves to new position
				node.coordinate.x += node.direction.getDirectionX();
				node.coordinate.y += node.direction.getDirectionY();
				shiftCoordinateIfNoWallCollsion(node.coordinate);
				if(node == head && intersectsWithBody(head.coordinate)) {
					collisionDetected = true;
				}
				Direction tmpDir = node.direction;
				node.direction = parentDir;
				parentDir = tmpDir;
				b = node.hasNext();
				node = node.next;
			}while(b);
		}
	}
	
	/*
	 * moves the snake-bodypart to the other side of the level when a wall is passed
	 */
	private void shiftCoordinateIfNoWallCollsion(Coordinate c) {
		Level l = Game.getRunningGame().getLevel();
		if(l.getWallCollision()) {
			return;
		}
		int maxX = l.getSizeX()-1;
		int maxY = l.getSizeY()-1;
		if(c.x > maxX) {
			c.x = 0;
		}
		if(c.x < 0) {
			c.x = maxX;
		}
		if(c.y > maxY) {
			c.y = 0;
		}
		if(c.y < 0) {
			c.y = maxY;
		}
	}
	
	private boolean collisionDetected = false;
	public boolean hasCollidedWithBody() {
		return collisionDetected;
	}
	
	public BodyNode getHead() {
		return head;
	}
	
	/**
	 * 
	 * @param c the coordinate that will be checked
	 * @return true when the coordinate is intersecting with a body coordinate, false otherwise
	 */
	private boolean intersectsWithBody(Coordinate c) {
		BodyNode n = head;
		while(n.hasNext()) {
			n = n.next();//skip head
			if(c.equals(n.coordinate)) {
				return true;
			}
			
		}
		return false;
	}
	/**
	 * 
	 * @param c the coordinate that will be checked
	 * @return true when the coordinate is intersecting with a body coordinate OR the head, false otherwise
	 */
	public boolean intersectsWith(Coordinate c) {
		for(Coordinate other : this) {
			if(other.equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	
	public class BodyNode implements Iterator<BodyNode>{
		
		Coordinate coordinate;
		protected BodyNode next;
		protected Direction direction;
		
		public BodyNode(Coordinate coord, BodyNode next, Direction dir) {
			this.coordinate = coord;
			this.next = next;
			this.direction = dir;
		}
		public BodyNode(Coordinate coord, BodyNode next) {
			this.coordinate = coord;
			this.next = next;
		}
		public BodyNode(Coordinate coord) {
			this(coord, null);
		}
		
		@Override
		public boolean hasNext() {
			return (this.next != null);
		}
		@Override
		public BodyNode next() {
			return next;
		}
	}


	@Override
	public Iterator<Coordinate> iterator() {
		return new Iterator<Coordinate>() {

			BodyNode n = new BodyNode(null, head);//pointer before initial segment
			
			@Override
			public boolean hasNext() {
				return n.hasNext();
			}

			@Override
			public Coordinate next() {
				n = n.next;
				return n.coordinate;
			}
		};
	}

}
