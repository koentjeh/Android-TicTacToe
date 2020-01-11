package com.koen.tictactoe.controller.computer;

import android.annotation.SuppressLint;
import android.util.Log;
import com.koen.tictactoe.controller.API.NetworkManager;
import com.koen.tictactoe.controller.API.VolleyCallback;
import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;
import org.json.JSONException;
import org.json.JSONObject;

public class BigDataComputer implements IComputer {

    private static final String TAG = "BigDataComputer";
    private static final String URL = "/computer/move";

    private Player computer;

    public BigDataComputer(Player computer) {
        this.computer = computer;
    }

    public Move makeMove(Board board) {
        final Move[] move = new Move[1];

        JSONObject board_root = new JSONObject();

        try {
            for (int y = 0; y < 3; y++) {
                JSONObject row = new JSONObject();
                for (int x = 0; x < 3; x++) {
                    row.put(x+"", board.getBoard()[y][x]);
                }
                board_root.put(y+"", row);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            NetworkManager.getInstance().postRequest(URL, board_root, new VolleyCallback<String>() {
                @SuppressLint("LongLogTag")
                @Override
                public void onSuccess(String response) {
                    Log.i(TAG, response);
                    move[0] = null;
                }

                @SuppressLint("LongLogTag")
                @Override
                public void onError(String error) {
                    Log.e(TAG, error);
                    move[0] = null;
                }
            });
        }

        return move[0];
    }
}
