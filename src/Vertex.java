public class Vertex {
    State stateFather;
    State stateSun;
    Action action;

    Vertex(State stateFather, State stateSun, Action action){
        this.stateFather = stateFather;
        this.stateSun = stateSun;
        this.action = action;
    }

}
