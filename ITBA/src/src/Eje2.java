package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.ws.alignment.qblast.BlastOutputParameterEnum;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

public class Eje2 {

    public static void run (String gene) throws Exception {

        String TYR = "MLLAVLYCLLWSFQTSAGHFPRACVSSKNLMEKECCPPWSGDRSPCGQLS" +
            "GRGSCQNILLSNAPLGPQFPFTGVDDRESWPSVFYNRTCQCSGNFMGFNC" +
            "GNCKFGFWGPNCTERRLLVRRNIFDLSAPEKDKFFAYLTLAKHTISSDYV" +
            "IPIGTYGQMKNGSTPMFNDINIYDLFVWMHYYVSMDALLGGSEIWRDIDF" +
            "AHEAPAFLPWHRLFLLRWEQEIQKLTGDENFTIPYWDWRDAEKCDICTDE" +
            "YMGGQHPTNPNLLSPASFFSSWQIVCSRLEEYNSHQSLCNGTPEGPLRRN" +
            "PGNHDKSRTPRLPSSADVEFCLSLTQYESGSMDKAANFSFRNTLEGFASP" +
            "LTGIADASQSSMHNALHIYMNGTMSQVQGSANDPIFLLHHAFVDSIFEQW" +
            "LRRHRPLQEVYPEANAPIGHNRESYMVPFIPLYRNGDFFISSKDLGYDYS" +
            "YLQDSDPDSFQDYIKSYLEQASRIWSWLLGAAMVGAVLTALLAGLVSLLC" +
            "RHKRKQLPEEKQPLLMEKEDYHSLYQSHL";

        String OCA2 = "MHLEGRDGRRYPGAPAVELLQTSVPSGLAELVAGKRRLPRGAGGADPSHS" +
            "CPRGAAGQSSWAPAGQEFASFLTKGRSHSSLPQMSSSRSKDSCFTENTPL" +
            "LRNSLQEKGSRCIPVYHPEFITAEESWEDSSADWERRYLLSREVSGLSAS" +
            "ASSEKGDLLDSPHIRLRLSKLRRCVQWLKVMGLFAFVVLCSILFSLYPDQ" +
            "GKLWQLLALSPLENYSVNLSSHVDSTLLQVDLAGALVASGPSRPGREEHI" +
            "VVELTQADALGSRWRRPQQVTHNWTVYLNPRRSEHSVMSRTFEVLTRETV" +
            "SISIRASLQQTQAVPLLMAHQYLRGSVETQVTIATAILAGVYALIIFEIV" +
            "HRTLAAMLGSLAALAALAVIGDRPSLTHVVEWIDFETLALLFGMMILVAI" +
            "FSETGFFDYCAVKAYRLSRGRVWAMIIMLCLIAAVLSAFLDNVTTMLLFT" +
            "PVTIRLCEVLNLDPRQVLIAEVIFTNIGGAATAIGDPPNVIIVSNQELRK" +
            "MGLDFAGFTAHMFIGICLVLLVCFPLLRLLYWNRKLYNKEPSEIVELKHE" +
            "IHVWRLTAQRISPASREETAVRRLLLGKVLALEHLLARRLHTFHRQISQE" +
            "DKNWETNIQELQKKHRISDGILLAKCLTVLGFVIFMFFLNSFVPGIHLDL" +
            "GWIAILGAIWLLILADIHDFEIILHRVEWATLLFFAALFVLMEALAHLHL" +
            "IEYVGEQTALLIKMVPEEQRLIAAIVLVVWVSALASSLIDNIPFTATMIP" +
            "VLLNLSHDPEVGLPAPPLMYALAFGACLGGNGTLIGASANVVCAGIAEQH" +
            "GYGFSFMEFFRLGFPMMVVSCTVGMCYLLVAHVVVGWN";

        String TYRP1 = "MSAPKLLSLGCIFFPLLLFQQARAQFPRQCATVEALRSGMCCPDLSPVSG" +
            "PGTDRCGSSSGRGRCEAVTADSRPHSPQYPHDGRDDREVWPLRFFNRTCH" +
            "CNGNFSGHNCGTCRPGWRGAACDQRVLIVRRNLLDLSKEEKNHFVRALDM" +
            "AKRTTHPLFVIATRRSEEILGPDGNTPQFENISIYNYFVWTHYYSVKKTF" +
            "LGVGQESFGEVDFSHEGPAFLTWHRYHLLRLEKDMQEMLQEPSFSLPYWN" +
            "FATGKNVCDICTDDLMGSRSNFDSTLISPNSVFSQWRVVCDSLEDYDTLG" +
            "TLCNSTEDGPIRRNPAGNVARPMVQRLPEPQDVAQCLEVGLFDTPPFYSN" +
            "STNSFRNTVEGYSDPTGKYDPAVRSLHNLAHLFLNGTGGQTHLSPNDPIF" +
            "VLLHTFTDAVFDEWLRRYNADISTFPLENAPIGHNRQYNMVPFWPPVTNT" +
            "EMFVTAPDNLGYTYEIQWPSREFSVPEIIAIAVVGALLLVALIFGTASYL" +
            "IRARRSMDEANQPLLTDQYQCYAEEYEKLQNPNQSVV";

        String SLC45A2 = "MGSNSGQAGRHIYKSLADDGPFDSVEPPKRPTSRLIMHSMAMFGREFCYA" +
            "VEAAYVTPVLLSVGLPSSLYSIVWFLSPILGFLLQPVVGSASDHCRSRWG" +
            "RRRPYILTLGVMMLVGMALYLNGATVVAALIANPRRKLVWAISVTMIGVV" +
            "LFDFAADFIDGPIKAYLFDVCSHQDKEKGLHYHALFTGFGGALGYLLGAI" +
            "DWAHLELGRLLGTEFQVMFFFSALVLTLCFTVHLCSISEAPLTEVAKGIP" +
            "PQQTPQDPPLSSDGMYEYGSIEKVKNGYVNPELAMQGAKNKNHAEQTRRA" +
            "MTLKSLLRALVNMPPHYRYLCISHLIGWTAFLSNMLFFTDFMGQIVYRGD" +
            "PYSAHNSTEFLIYERGVEVGCWGFCINSVFSSLYSYFQKVLVSYIGLKGL" +
            "YFTGYLLFGLGTGFIGLFPNVYSTLVLCSLFGVMSSTLYTVPFNLITEYH" +
            "REEEKEVCCH";

        String currentGene;

        if ("TYR".equals(gene.toUpperCase())) {
            currentGene = TYR;
        } else if ("SLC45A2".equals(gene.toUpperCase())) {
            currentGene = SLC45A2;
        } else if ("TYRP1".equals(gene.toUpperCase())) {
            currentGene = TYRP1;
        } else if ("OCA2".equals(gene.toUpperCase())) {
            currentGene = OCA2;
        } else {
            throw new Exception("NO VALID GENE --> AVAILABLE GENES : TYR, SLC45A2, OCA2, TYRP1");
        }


        final NCBIQBlastService ncbiqBlastService = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        props.setBlastProgram(BlastProgramEnum.blastp);
        props.setBlastDatabase("swissprot");
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        outputProps.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,"200");
        String rid = ncbiqBlastService.sendAlignmentRequest(currentGene, props);

        // wait until results become available. Alternatively, one can do other computations/send other alignment requests
        while (!ncbiqBlastService.isReady(rid)) {
            System.out.println("Waiting for results. Sleeping for 5 seconds");
            Thread.sleep(5000);
        }

        final File f = new File(gene.toUpperCase() + "_blast.txt");
        final File baseSequencesEje3File = new File("eje3_base_sequences.txt");

        final InputStream in = ncbiqBlastService.getAlignmentResults(rid, outputProps);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        final FileWriter eje3FileWriter = new FileWriter(baseSequencesEje3File);
        final FileWriter writer = new FileWriter(f);

        String line;
        Pattern nameSequencePattern = Pattern.compile("\\s<Hit_id>(.*)</Hit_id>");
        Pattern proteinSequencePattern = Pattern.compile("\\s<Hsp_qseq>(.*)</Hsp_qseq>");

        boolean flag = false;
        Matcher proteinMatcher, nameMatcher;
        while ((line = reader.readLine()) != null) {
            writer.write(line + '\n');

            nameMatcher = nameSequencePattern.matcher(line);
            if (nameMatcher.find()) {
                flag = true;
                eje3FileWriter.write(nameMatcher.group(1) + ":");
            }
            proteinMatcher = proteinSequencePattern.matcher(line);
            if (proteinMatcher.find() && flag) {
                flag = false;
                eje3FileWriter.write(proteinMatcher.group(1) + "\n");
            }
        }

        IOUtils.close(writer);
        IOUtils.close(reader);
        IOUtils.close(eje3FileWriter);
        // delete given alignment results from blast server (optional operation)
        ncbiqBlastService.sendDeleteRequest(rid);

    }
}
