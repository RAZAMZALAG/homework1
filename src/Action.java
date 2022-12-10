/**
 * נצא מנקודת הנחה שיש לנו אריח בודד וצריך לבדוק איפה האפס ביחס אליו.
 *אם האפס נמצא בצד שמאל- נמצא בשורה זהה והאינדק מינוס אחד.
 * אם האפס נמצא למעלה העמודה זהה והשורה מינוס 1 (של האפס)
 *אם האפס נמצא למטה- אז העמודה זהה והשורה של האפס מינוס 1
 *.נבדוק אם האפס נמצא בצד ימין לאריח - צריך להיות באותה שורה ובעמודה צריך להיות פלוס 1
 */
public class Action {
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }

    public Tile getTile() {
        return tile;
    }

    public Direction getDirection() {
        return direction;
    }

    public String toString(){
        String x =  "Move" + tile + direction;
        System.out.println(x);
        return x;
    }

}


