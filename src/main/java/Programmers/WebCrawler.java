package Programmers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class WebCrawler {
    public static void main(String[] args) {
        String baseUrl = "https://school.programmers.co.kr/learn/courses/30/lessons/42840";
        try {
            // 웹 페이지 크롤링 (문제 설명 페이지)
            Document document = Jsoup.connect(baseUrl).get();

            // 문제 제목 가져오기 및 파일명으로 변환
            Element titleElement = document.selectFirst("li.active"); // 제목이 포함된 li 요소 선택
            String title = titleElement != null ? titleElement.text() : "제목 없음";
            String fileName = title.replace(" ", "_").replaceAll("[^\\p{L}\\d_]", "_");

            // 문제 설명 전체 추출
            Element markdownDiv = document.selectFirst("div.markdown");
            StringBuilder problemDescriptionMarkdown = new StringBuilder();

            if (markdownDiv != null) {
                Elements children = markdownDiv.children();
                for (Element child : children) {
                    String tagName = child.tagName();
                    if (tagName.matches("h\\d+") && child.text().contains("제한사항")) {
                        // "제한사항" 헤딩을 만나면 문제 설명 부분 종료
                        break;
                    }
                    // 문제 설명 내용 추가
                    if (!child.text().isEmpty()) {
                        if (tagName.equals("p")) {
                            problemDescriptionMarkdown.append("\n").append(child.text()).append("\n");
                        } else if (tagName.equals("ul")) {
                            problemDescriptionMarkdown.append(parseList(child)).append("\n");
                        } else if (tagName.equals("table")) {
                            // 테이블 앞에 빈 줄 추가
                            problemDescriptionMarkdown.append("\n");
                            problemDescriptionMarkdown.append(convertTableToMarkdown(child)).append("\n");
                        } else if (tagName.matches("h\\d+")) {
                            problemDescriptionMarkdown.append("\n").append("#### ").append(child.text()).append("\n");
                        } else {
                            problemDescriptionMarkdown.append(child.text()).append("\n");
                        }
                    }
                }
            } else {
                problemDescriptionMarkdown.append("문제 설명을 불러올 수 없습니다.");
            }

            // 이하 나머지 코드는 변경 없음
            // 제한사항 추출
            Element limitationsHeading = document.selectFirst("div.markdown h5:contains(제한사항)");
            StringBuilder limitationsMarkdown = new StringBuilder();

            if (limitationsHeading != null) {
                Element nextElement = limitationsHeading.nextElementSibling();
                while (nextElement != null && !nextElement.tagName().matches("h\\d+")) {
                    if (nextElement.tagName().equals("ul")) {
                        limitationsMarkdown.append(parseList(nextElement));
                    }
                    nextElement = nextElement.nextElementSibling();
                }
            } else {
                limitationsMarkdown.append("제한사항을 불러올 수 없습니다.");
            }

            // 입출력 예시 추출
            Elements tables = document.select("table");  // 모든 테이블 선택

            // HTML 테이블을 Markdown 테이블로 변환
            StringBuilder tableMarkdown = new StringBuilder();
            for (Element table : tables) {
                tableMarkdown.append(convertTableToMarkdown(table)).append("\n\n");
            }

            // 입출력 예 설명 추출
            Element exampleExplanationHeading = document.selectFirst("div.markdown h5:contains(입출력 예 설명)");
            StringBuilder exampleDescriptionMarkdown = new StringBuilder();

            if (exampleExplanationHeading != null) {
                Element nextElement = exampleExplanationHeading.nextElementSibling();
                while (nextElement != null && !nextElement.tagName().matches("h\\d+")) {
                    String tagName = nextElement.tagName();
                    if (!nextElement.text().isEmpty()) {
                        if (tagName.equals("p")) {
                            exampleDescriptionMarkdown.append("\n").append(nextElement.text()).append("\n");
                        } else if (tagName.equals("ul")) {
                            exampleDescriptionMarkdown.append(parseList(nextElement)).append("\n");
                        } else if (tagName.matches("h\\d+")) {
                            exampleDescriptionMarkdown.append("\n").append(nextElement.text()).append("\n");
                        } else {
                            exampleDescriptionMarkdown.append(nextElement.text()).append("\n");
                        }
                    }
                    nextElement = nextElement.nextElementSibling();
                }
            } else {
                exampleDescriptionMarkdown.append("입출력 예 설명을 불러올 수 없습니다.");
            }

            // Markdown 포맷으로 파일 구성
            String markdownContent = "### " + title + "\n\n" +
                    "#### 문제 설명\n" + problemDescriptionMarkdown.toString() + "\n" +
                    "---\n\n" +
                    "#### 제한사항\n\n" +
                    limitationsMarkdown.toString() + "\n" +
                    "---\n\n" +
                    "#### 입출력 예\n" + tableMarkdown.toString() + "\n" +
                    "---\n\n" +
                    "#### 입출력 예 설명\n" + exampleDescriptionMarkdown.toString();

            // Markdown 파일 저장
            Writer writer = new OutputStreamWriter(new FileOutputStream("src/" + fileName + ".md"), StandardCharsets.UTF_8);
            writer.write(markdownContent);
            writer.close();

            // Java 파일에 지정된 템플릿 작성
            String javaTemplate = "public class " + fileName + " {\n\n" +
                    "    public static int solution() {\n" +
                    "        int answer = 0;\n\n" +
                    "        return answer;\n" +
                    "    }\n\n" +
                    "    public static void main(String[] args) {\n\n" +
                    "        System.out.println(solution());\n\n" +
                    "    }\n" +
                    "}";

            // Java 파일 저장
            Writer codeWriter = new OutputStreamWriter(new FileOutputStream("src/" + fileName + ".java"), StandardCharsets.UTF_8);
            codeWriter.write(javaTemplate);
            codeWriter.close();

            System.out.println(fileName + ".java 파일이 성공적으로 생성되었습니다!");
            System.out.println(fileName + ".md 파일이 성공적으로 생성되었습니다!");
        } catch (IOException e) {
            System.err.println("오류가 발생했습니다: " + e.getMessage());
        }
    }

    // HTML 테이블을 Markdown 테이블로 변환하는 함수
    private static String convertTableToMarkdown(Element table) {
        StringBuilder markdown = new StringBuilder();

        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            Elements cells = rows.get(i).select("th, td");
            for (Element cell : cells) {
                markdown.append("| ").append(cell.text()).append(" ");
            }
            markdown.append("|\n");

            // 첫 번째 줄(헤더) 아래에 구분선 추가
            if (i == 0) {
                for (Element cell : cells) {
                    markdown.append("|---");
                }
                markdown.append("|\n");
            }
        }
        return markdown.toString();
    }

    // 중첩된 리스트를 파싱하는 함수
    private static String parseList(Element ulElement) {
        StringBuilder listMarkdown = new StringBuilder();
        parseListItems(ulElement, listMarkdown, 0);
        return listMarkdown.toString();
    }

    private static void parseListItems(Element ulElement, StringBuilder builder, int depth) {
        Elements listItems = ulElement.select("> li");
        for (Element li : listItems) {
            for (int i = 0; i < depth; i++) {
                builder.append("  "); // 들여쓰기
            }
            builder.append("- ").append(li.ownText()).append("\n");
            // 하위 ul 요소가 있는지 확인
            Elements subUl = li.select("> ul");
            if (!subUl.isEmpty()) {
                parseListItems(subUl.first(), builder, depth + 1);
            }
        }
    }
}
