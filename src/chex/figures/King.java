package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class King extends Figure {
    public static final int INVALID_KING_MOVE = 601;
    public static final int OWN_PIECE_COLLISION_ERROR = 602;

    private boolean hasMoved;

    public King(Player owner) {
        hasMoved = false;
        owner.registerKing(this);
    }

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.abs().max() == 1) {
            if (board[to.getY()][to.getX()].getPiece() != null
                && board[to.getY()][to.getX()].getPiece().belongsTo(player)) {
                return OWN_PIECE_COLLISION_ERROR;
            } else {
                if (!hasMoved) {
                    hasMoved = true;
                    player.setMovedKing(true);
                }
                return 0;
            }
        } else if (!hasMoved) {
            //castling
            if (moveVector.equals(new Vector2d(2, 0)) && !player.hasMovedRightRook()) {
                //todo: impl

                hasMoved = true;
                player.setMovedKing(true);
                return 0;
            } else if (moveVector.equals(new Vector2d(-2 ,0)) && !player.hasMovedLeftRook()) {
                //todo: impl

                hasMoved = true;
                player.setMovedKing(true);
                return 0;
            } else {
                return INVALID_KING_MOVE;
            }
        } else {
            return INVALID_KING_MOVE;
        }
    }

    @Override
    public String toString() {
        return "K";
    }
}
