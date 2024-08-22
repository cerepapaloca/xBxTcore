package Plugin.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class ColorUtils {

    public static String modifyColorHexWithHLS(String hexColor, float hueDelta, float lightnessDelta, float saturationDelta) {
        // 1. Convert Hex to RGB
        int r = Integer.valueOf(hexColor.substring(1, 2), 16);
        int g = Integer.valueOf(hexColor.substring(3, 4), 16);
        int b = Integer.valueOf(hexColor.substring(4, 6), 16);

        // 2. Convert RGB to HLS
        float[] hls = rgbToHLS(r, g, b);

        // 3. Modify HLS values
        hls[0] = (hls[0] + hueDelta) % 1.0f; // hue
        hls[1] = clamp(hls[1] + lightnessDelta, 0, 1); // lightness
        hls[2] = clamp(hls[2] + saturationDelta, 0, 1); // saturation

        // 4. Convert HLS back to RGB
        int[] rgb = hlsToRGB(hls[0], hls[1], hls[2]);

        // 5. Convert RGB back to Hex
        return String.format("#%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
    }

    private static float[] rgbToHLS(int r, int g, int b) {
        float rf = r / 255.0f;
        float gf = g / 255.0f;
        float bf = b / 255.0f;

        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));

        float h = 0, s, l = (max + min) / 2;

        if (max == min) {
            h = s = 0; // achromatic
        } else {
            float delta = max - min;
            s = l > 0.5 ? delta / (2 - max - min) : delta / (max + min);
            if (max == rf) {
                h = (gf - bf) / delta + (gf < bf ? 6 : 0);
            } else if (max == gf) {
                h = (bf - rf) / delta + 2;
            } else {
                h = (rf - gf) / delta + 4;
            }
            h /= 6;
        }

        return new float[]{h, l, s};
    }

    public static int[] hlsToRGB(float h, float l, float s) {
        int r, g, b;

        if (s == 0) {
            r = g = b = Math.round(l * 255); // achromatic
        } else {
            float q = l < 0.5 ? l * (1 + s) : (l + s) - (l * s);
            float p = 2 * l - q;
            r = Math.round(255 * hueToRGB(p, q, h + 1.0f / 3));
            g = Math.round(255 * hueToRGB(p, q, h));
            b = Math.round(255 * hueToRGB(p, q, h - 1.0f / 3));
        }

        return new int[]{r, g, b};
    }

    public static float hueToRGB(float p, float q, float t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1.0 / 6) return p + (q - p) * 6 * t;
        if (t < 1.0 / 2) return q;
        if (t < 2.0 / 3) return (float) (p + (q - p) * (2.0 / 3 - t) * 6);
        return p;
    }

    private static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static String blockToHex(Material material) {
        String name = material.name();
        if (name.contains("BLACK")) {
            return "1D1D21";
        } else if (name.contains("RED")) {
            return "B02E26";
        } else if (name.contains("GREEN")) {
            return "5E7C16";
        } else if (name.contains("BROWN")) {
            return "835432";
        } else if (name.contains("BLUE")) {
            return "3C44AA";
        } else if (name.contains("PURPLE")) {
            return "8932B8";
        } else if (name.contains("CYAN")) {
            return "169C9C";
        } else if (name.contains("LIGHT_GRAY")) {
            return "9D9D97";
        } else if (name.contains("GRAY")) {
            return "474F52";
        } else if (name.contains("PINK")) {
            return "F38BAA";
        } else if (name.contains("LIME")) {
            return "80C71F";
        } else if (name.contains("YELLOW")) {
            return "FED83D";
        } else if (name.contains("LIGHT_BLUE")) {
            return "3AB3DA";
        } else if (name.contains("MAGENTA")) {
            return "C74EBD";
        } else if (name.contains("ORANGE")) {
            return "F9801D";
        } else if (name.contains("WHITE")) {
            return "F9FFFE";
        }
        return "000000";
    }

    public static String applyGradient(String input) {
        input = org.bukkit.ChatColor.translateAlternateColorCodes('&', input);
        String startTag = input.substring(input.indexOf("<#") + 2, input.indexOf(">")).replace("#", "");
        String endTag = input.substring(input.lastIndexOf("<#") + 2, input.lastIndexOf(">")).replace("#", "");

        String text = input.substring(input.indexOf(">") + 1, input.lastIndexOf("<"));
        int startColor = Integer.parseInt(startTag, 16);
        int endColor = Integer.parseInt(endTag, 16);

        int length = text.length();
        StringBuilder gradientText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            int red = (int) ((1 - ratio) * ((startColor >> 16) & 0xFF) + ratio * ((endColor >> 16) & 0xFF));
            int green = (int) ((1 - ratio) * ((startColor >> 8) & 0xFF) + ratio * ((endColor >> 8) & 0xFF));
            int blue = (int) ((1 - ratio) * (startColor & 0xFF) + ratio * (endColor & 0xFF));
            String hexColor = String.format("#%02x%02x%02x", red, green, blue);
            gradientText.append(ChatColor.of(hexColor)).append(text.charAt(i));
        }

        return gradientText.toString();
    }
}
