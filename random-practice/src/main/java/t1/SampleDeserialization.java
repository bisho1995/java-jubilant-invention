package t1;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

public class SampleDeserialization {
    public static void main(String[] args) {
        String content = "{\"passowrdDes\":\"GB0Ogj1k1vfv43NXDkJ1BKqm3/Lz1y2yq4CsBhT3ITG6an+tTaW0IONEsbk/NmH9afDkQel73Iat9QlItGhHfl5HJ711NxZggTwcJnamTI2XqWcNhMe/nV72ilIHI2btoZ8jywcif6Fi4mdKd109USUrVHxzuLdze1B6rP1qi8wLqSJwUXS8ckkYU4WZ6AASaurFg+jLwrxg/0hqsXUyfkzW6ycKfaaZG/xFoRQpzoKrUoqrinmiVE21EVtBX+nwcKgQONMmprZhuYtQwwLaxaZWe52H/rNruc0l4eFFEtN+M3k/Ae8OKOxCZfVK4YDRc0JHZc7Ig+wb7lUdR8QH7Q==\",\"sign\":\"KM2AnMfVC89w-_fUc9aUVX-C6eso8bO4-APHxEq5eDlC8RUMx6sjiynoKg8gMSH9dHjxqi-Mt14T8Mta6UYVCDC1URnFvG_tzYhND9NKXexjtYSu3GbSrDADHaUqICBf6wL1jiZbYVlRxNJA65SMUET8ioi0weskpSCzgqgHI7NBpgAGgKo2erTNwAYk-aAqHqjxVf9600b12l9id70nS0pIxbuYv9DS6KuSF8j20qIMN8u4nRO1mSix0plLdF44kKXNxf-F5WV9XfKRrXW2LGOnUgkcvDPZcQquIu-w2jwtD6PGclGD3kGmyBPbKbUEoERPjJwpTsM03OnBbPLgZg\"}";

        T104ResponseData t104ResponseData = new Gson().fromJson(content, T104ResponseData.class);

        System.out.println(t104ResponseData);
    }


    @Getter
    @ToString
    public class T104ResponseData {
        @SerializedName("passowrdDes")
        private String passwordDes;
        private String sign;
    }
}
