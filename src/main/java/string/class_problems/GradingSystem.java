import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface Question {
    double evaluateScore();
}

// ========================================================
// CONCRETE IMPLEMENTATIONS (Encapsulating Grading Logic)
// ========================================================
class MultipleChoiceQuestion implements Question {
    private final String correctAnswer;
    private final String studentAnswer;
    private final double points;

    public MultipleChoiceQuestion(String correctAnswer, String studentAnswer, double points) {
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    @Override
    public double evaluateScore() {
        return correctAnswer.equals(studentAnswer) ? points : 0.0;
    }
}

class TrueFalseQuestion implements Question {
    private final String correctAnswer;
    private final String studentAnswer;
    private final double points;

    public TrueFalseQuestion(String correctAnswer, String studentAnswer, double points) {
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    @Override
    public double evaluateScore() {
        return correctAnswer.equalsIgnoreCase(studentAnswer) ? points : 0.0;
    }
}

class EssayQuestion implements Question {
    private final String commaSeparatedKeywords;
    private final String studentAnswer;
    private final double points;

    public EssayQuestion(String correctAnswer, String studentAnswer, double points) {
        this.commaSeparatedKeywords = correctAnswer;
        this.studentAnswer = studentAnswer.toLowerCase();
        this.points = points;
    }

    @Override
    public double evaluateScore() {
        String[] keywords = commaSeparatedKeywords.split(",");
        int matchedCount = 0;

        for (String keyword : keywords) {
            String trimmedKeyword = keyword.trim().toLowerCase();
            if (!trimmedKeyword.isEmpty() && studentAnswer.contains(trimmedKeyword)) {
                matchedCount++;
            }
        }

        if (matchedCount >= 2) {
            return points * 0.75;
        } else if (matchedCount == 1) {
            return points * 0.50;
        } else {
            return 0.0;
        }
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class GradingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double overallScore = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextLine()) break;

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            // Extract the question type token out first
            String[] firstSplit = line.split("\\s+", 2);
            String type = firstSplit[0].toUpperCase();
            String remaining = firstSplit[1];

            // Parse text values safely by scanning text blocks wrapped inside matching quotes
            String[] tokens = parseQuotedTokens(remaining);

            // tokens[0] = QuestionText, tokens[1] = CorrectAnswer, tokens[2] = StudentAnswer
            String correctAnswer = tokens[1];
            String studentAnswer = tokens[2];
            double points = Double.parseDouble(tokens[3]);

            Question question;

            switch (type) {
                case "MCQ":
                    question = new MultipleChoiceQuestion(correctAnswer, studentAnswer, points);
                    break;
                case "TF":
                    question = new TrueFalseQuestion(correctAnswer, studentAnswer, points);
                    break;
                case "ESSAY":
                    question = new EssayQuestion(correctAnswer, studentAnswer, points);
                    break;
                default:
                    System.out.println("Unknown question type: " + type);
                    continue;
            }

            // Uniformly grade individual components polymorphically
            double calculatedScore = question.evaluateScore();
            System.out.printf("%s: %.2f\n", type, calculatedScore);

            overallScore += calculatedScore;
        }

        System.out.printf("Total Score: %.2f\n", overallScore);
        scanner.close();
    }

    /**
     * Helper parser utility to capture strings safely inside quotes 
     * while preserving multi-word structures and identifying final numerical flags.
     */
    private static String[] parseQuotedTokens(String rawInput) {
        String[] result = new String[4];
        int tokenIndex = 0;
        
        int i = 0;
        while (i < rawInput.length() && tokenIndex < 3) {
            // Locate the opening quote boundary
            if (rawInput.charAt(i) == '"') {
                int start = i + 1;
                int end = rawInput.indexOf('"', start);
                if (end != -1) {
                    result[tokenIndex++] = rawInput.substring(start, end);
                    i = end + 1;
                    continue;
                }
            }
            i++;
        }
        
        // Grab remaining numeric characters left at the tail end
        if (i < rawInput.length()) {
            result[3] = rawInput.substring(i).trim();
        }
        
        return result;
    }
}
