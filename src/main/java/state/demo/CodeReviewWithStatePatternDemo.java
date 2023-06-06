package state.demo;

/**
 * https://www.notion.so/yunxianghe/State-6814f6151df54ead9c2817c555abbe1a?pvs=4
 *
 * @Author Yunxiang He
 */
public class CodeReviewWithStatePatternDemo {

    public static void main(String[] args) {
        CodeReview cr = new CodeReview();
        cr.createNewReview();
        cr.sendOutForReview();
        cr.commentOnTheReview();
        cr.createNewRevision();
        cr.sendOutForReview();
        cr.approveTheReview();
        cr.closeTheReview();
    }

    public static class CodeReview {

        private final State nullState;
        private final State draftState;
        private final State reviewState;
        private final State commentedState;
        private final State approvedState;
        private final State closedState; // New added state
        private State currentState;

        public CodeReview() {
            nullState = new NullState(this);
            draftState = new DraftState(this);
            reviewState = new ReviewState(this);
            commentedState = new CommentedState(this);
            approvedState = new ApprovedState(this);
            closedState = new ClosedState(this);
            this.currentState = nullState;
        }

        public void createNewReview() {
            currentState.createNewReview();
        }

        public void createNewRevision() {
            currentState.createNewRevision();
        }

        public void sendOutForReview() {
            currentState.sendOutForReview();
        }

        public void commentOnTheReview() {
            currentState.commentOnTheReview();
        }

        public void approveTheReview() {
            currentState.approveTheReview();
        }

        public void closeTheReview() {
            currentState.closeTheReview();
        }

        abstract class State {
            protected final CodeReview context;

            public State(CodeReview context) {
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

            public NullState(CodeReview context) {
                super(context);
            }

            @Override
            public void createNewReview() {
                System.out.println("Creating new review");
                currentState = draftState;
            }
        }

        public class DraftState extends State {

            public DraftState(CodeReview context) {
                super(context);
            }

            @Override
            public void sendOutForReview() {
                System.out.println("Sending out for the review");
                currentState = reviewState;
            }

            @Override
            public void closeTheReview() {
                System.out.println("Closing the review");
                currentState = closedState;
            }
        }

        public class ReviewState extends State {

            public ReviewState(CodeReview context) {
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

            public CommentedState(CodeReview context) {
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

            public ApprovedState(CodeReview context) {
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

            public ClosedState(CodeReview context) {
                super(context);
            }

        }
    }

}
