package neverlang.examples.statemachine.runtime;

import java.util.*;
import neverlang.runtime.*;

public class ExecutionContext {
    private final String name;
    private ASTNode statechart;
    private final Map<String, ExecutionContext> substates = new HashMap<>();
    private final Deque<ExecutionContext> nestedContexts = new ArrayDeque<>();

    private String currentState = null;
    private final Queue<String> eventQueue = new LinkedList<>();

    public ExecutionContext(String name) {
        this.name = name;
    }

    public void setStateChart(ASTNode chart) {
        this.statechart = chart;
    }

    public void registerSubContext(String name, ExecutionContext ctx) {
        substates.put(name, ctx);
    }

    public ExecutionContext getSubContext(String name) {
        return substates.get(name);
    }

    public void pushNestedContext(ExecutionContext ctx) {
        nestedContexts.push(ctx);
    }

    public void initialize() {
        System.out.println("[ExecutionContext] Initializing: " + name);

        // Find the StartState in the statechart AST
        if (statechart != null) {
            for (ASTNode child : statechart.getChildren()) {
                if ("StartState".equals(child.getProductionName())) {
                    ASTNode target = child.getChild(0); // assume ID is first child
                    currentState = target.getToken().getLexeme();
                    System.out.println("[ExecutionContext] Starting at state: " + currentState);
                    break;
                }
            }
        }
    }

    public void sendEvent(String eventName) {
        eventQueue.add(eventName);
    }

    public void run() {
        System.out.println("[ExecutionContext] Running: " + name);

        while (!eventQueue.isEmpty()) {
            String event = eventQueue.poll();
            System.out.println("[ExecutionContext] Processing event: " + event);

            boolean transitioned = false;

            for (ASTNode child : statechart.getChildren()) {
                if ("Transition".equals(child.getProductionName())) {
                    ASTNode fromNode = child.getChild(0);
                    ASTNode toNode = child.getChild(1);
                    ASTNode body = child.getChild(2);

                    String from = fromNode.getToken().getLexeme();
                    String to = toNode.getToken().getLexeme();

                    if (!from.equals(currentState))
                        continue;

                    // Look for trigger node in the body
                    for (ASTNode bodyPart : body.getChildren()) {
                        if ("Trigger".equals(bodyPart.getProductionName())) {
                            String triggerName = bodyPart.getChild(0).getToken().getLexeme();
                            if (triggerName.equals(event)) {
                                System.out.println(
                                        "[ExecutionContext] Transition: " + from + " -> " + to + " on " + event);
                                currentState = to;
                                transitioned = true;
                                break;
                            }
                        }
                    }

                    if (transitioned)
                        break;
                }
            }

            if (!transitioned) {
                System.out.println("[ExecutionContext] No transition from " + currentState + " on event " + event);
            }
        }

        System.out.println("[ExecutionContext] Final state: " + currentState);
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getName() {
        return name;
    }
}
