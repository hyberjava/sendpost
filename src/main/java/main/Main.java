package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://127.0.0.1:8080/signin";

    private static final String MAIN_EXCEPTION = "SOMETHING GOING WRONG";

    private static final String POST_URL_SIGNIN = "http://127.0.0.1:8080/signin";

    private static final String POST_URL_SIGNUP = "http://127.0.0.1:8080/signup";

    private static final String POST_PARAMS = "login=newUser&password=newPassword";

    private static final String WAITING_RESPONSE = "waiting response code 200 / ";

    private static final String WAITING_REQUEST = "waiting request Authorized / ";

    private static final String RESPONSE_REQUEST_SUCCESS = " getting :::: RESPONSE REQUEST SUCCESS";

    private static final String RESPONSE_REQUEST_FAIL = " getting :::: RESPONSE REQUEST FAIL";

    private static final String RESPONSE_CODE_SUCCESS = " getting :::: RESPONSE CODE SUCCESS";

    private static final String RESPONSE_CODE_FILED = " getting :::: RESPONSE CODE FAILED";

    private static final String FIRST_TEST_STARTED = "========= FIRST TEST STARTED =========";

    private static final String SECOND_TEST_STERTED = "========= SECOND TEST STARTED =========";

    private static final String FIRST_TEST_PASSED = "========= FIRST TEST PASSED =========\n";

    private static final String FIRST_TEST_FAILED = "========= FIRST TEST FAILED =========\n";

    private static final String SECOND_TEST_PASSED = "========= SECOND TEST PASSED =========\n";

    private static final String SECOND_TEST_FAILED = "========= SECOND TEST FAILED =========\n";

    private static final String RIGHT_RESPONSE_CODE = "200";

    private static final String RIGHT_REQUEST = "Authorized";

    public static void main(String[] args) {
        try {
            String[] responseSignUp = sendPOST(POST_URL_SIGNUP);
            System.out.println(FIRST_TEST_STARTED);
            if (responseSignUp[0].equals(RIGHT_RESPONSE_CODE)) {
                System.out.println(WAITING_RESPONSE + responseSignUp[0] + RESPONSE_CODE_SUCCESS);
                System.out.println(FIRST_TEST_PASSED);
            } else {
                System.out.println(WAITING_RESPONSE + responseSignUp[0] + RESPONSE_CODE_FILED);
                System.out.println(FIRST_TEST_FAILED);
            }
            String[] responseSignIn = sendPOST(POST_URL_SIGNIN);
            System.out.println(SECOND_TEST_STERTED);
            if (responseSignIn[0].equals(RIGHT_RESPONSE_CODE)) {
                System.out.println(WAITING_RESPONSE + responseSignIn[0] + RESPONSE_CODE_SUCCESS);
                if (responseSignIn[1].equals(RIGHT_REQUEST)) {
                    System.out.println(WAITING_REQUEST + responseSignIn[1] + RESPONSE_REQUEST_SUCCESS);
                    System.out.println(SECOND_TEST_PASSED);
                } else {
                    System.out.println(WAITING_REQUEST + responseSignIn[1] + RESPONSE_REQUEST_FAIL);
                }
            } else {
                System.out.println(WAITING_RESPONSE + responseSignIn[0] + RESPONSE_CODE_FILED);
                System.out.println(SECOND_TEST_FAILED);
            }
        } catch (IOException e) {
            System.out.println(MAIN_EXCEPTION + ", " + e.getMessage());
        }
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static String[] sendPOST(String postUrl) throws IOException {
        String[] responses = new String[2];
        URL obj = new URL(postUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        //System.out.println("POST Response Code :: " + responseCode);

        StringBuffer response = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } else {
            //System.out.println("POST request not worked");
            response.append("WRONG RESPONSE CODE");
        }

        responses[0] = "" + responseCode;
        responses[1] = response.toString();
        return responses;
    }
}
