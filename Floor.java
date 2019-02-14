public class Floor implements Comparable<Floor>{

static int curr;

int floor;

Floor(int floor){
  this.floor=floor;
}

@Override
public int compareTo(Floor other){
  return Integer.compare( Math.abs(this.floor-curr), Math.abs(other.floor-curr) );
}

@Override
public String toString(){
  return ""+this.floor;
}

}
