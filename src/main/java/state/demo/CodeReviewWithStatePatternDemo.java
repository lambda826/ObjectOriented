package state.demo;

/**
 * @Author Yunxiang He
 */
public class CodeReviewWithStatePatternDemo {

    private final State nullState;
    private final State draftState;
    private final State reviewState;
    private final State commentedState;
    private final State approvedState;
    private final State closedState; // New added state
    private State currentState;

    public CodeReviewWithStatePatternDemo(State currentState) {
        nullState = new NullState(this);
        draftState = new DraftState(this);
        reviewState = new ReviewState(this);
        commentedState = new CommentedState(this);
        approvedState = new ApprovedState(this);
        closedState = new ApprovedState(this);
        this.currentState = currentState;
    }

    abstract class State {
        protected final CodeReviewWithStatePatternDemo context;

        public State(CodeReviewWithStatePatternDemo context) {
            this.context = context;
        }

        protected void createNewReview() {
            System.out.println("Illegal operation");
        }

        protected void createNewRevision() {
            System.out.println("Illegal operation");
        }

        protected void sendOutForReview() {
            System.out.println("Illegal operation");
        }

        protected void commentOnTheReview() {
            System.out.println("Illegal operation");
        }

        protected void approveTheReview() {
            System.out.println("Illegal operation");
        }

        protected void closeTheReview() {
            System.out.println("Illegal operation");
        }
    }

    public class NullState extends State {

        public NullState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }

        @Override
        public void createNewReview() {
            System.out.println("Creating new review");
            context.currentState = this.context.draftState;
        }
    }

    public class DraftState extends State {

        public DraftState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }

        @Override
        public void sendOutForReview() {
            System.out.println("Sending out for the review");
        }

        @Override
        public void closeTheReview() {
            System.out.println("Closing the review");
        }
    }

    public class ReviewState extends State {

        public ReviewState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }

        @Override
        public void commentOnTheReview() {
            System.out.println("Commenting the review");
            currentState = commentedState;
        }

        @Override
        public void approveTheReview() {
            System.out.println("Approving the review");
            currentState = approvedState;
        }

        @Override
        public void closeTheReview() {
            System.out.println("Closing the review");
            currentState = closedState;
        }
    }

    public class CommentedState extends State {

        public CommentedState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }


        @Override
        public void createNewRevision() {
            System.out.println("Creating new revision");
            currentState = draftState;
        }

        @Override
        public void closeTheReview() {
            System.out.println("Closing the review");
            currentState = closedState;
        }
    }

    public class ApprovedState extends State {

        public ApprovedState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }

        @Override
        public void closeTheReview() {
            System.out.println("Closing the review");
            currentState = closedState;
        }
    }

    /**
     * New added state
     */
    public class ClosedState extends State {

        public ClosedState(CodeReviewWithStatePatternDemo context) {
            super(context);
        }
    }
}

