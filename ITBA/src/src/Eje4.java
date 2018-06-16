package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eje4 {

    public static void run (final String gene, final String patternToFind) throws Exception {
        if (!"TYR".equals(gene.toUpperCase()) && !"SLC45A2".equals(gene.toUpperCase()) && !"TYRP1".equals(gene.toUpperCase()) && !"OCA2".equals(gene.toUpperCase())) {
            throw new Exception("NO VALID GENE --> AVAILABLE GENES : TYR, SLC45A2, OCA2, TYRP1");
        }
        final FileWriter eje4FileWriter = new FileWriter(new File(gene.toUpperCase() + "_eje4.txt"));

        BufferedReader br = new BufferedReader(new FileReader(gene.toUpperCase() + "_blast.txt"));
        String line;
        Pattern pattern = Pattern.compile("(.*)" + patternToFind + "(.*)");
        Matcher proteinMatcher;
        boolean found = false;
        StringBuilder hitBuffer = new StringBuilder();
        while ((line = br.readLine()) != null) {

            proteinMatcher = pattern.matcher(line);
            if (proteinMatcher.find()) {
                found = true;
            }

            if (line.startsWith("</Hit>")) {
                if (found) {
                    hitBuffer.append(line + "\n");
                    eje4FileWriter.write(hitBuffer.toString());
                }
                hitBuffer = new StringBuilder();
                found = false;
            } else {
                hitBuffer.append(line + "\n");
            }
        }
        eje4FileWriter.close();
        br.close();
    }
}
