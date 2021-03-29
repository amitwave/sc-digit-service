package com.sc.np;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DigitServiceImpl implements DigitService{

    private List<String> readFile(String fileName){

        ClassPathResource resource = new ClassPathResource(fileName);

        List<String> lines = null;
        try (Stream<String> stream = Files.lines(Paths.get(resource.getFile().getPath()))) {

            lines = stream.filter(s-> s.trim().length()>0).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName);
        }
        return lines;
    }

    public String getNumbers(String fileName){

        StringBuilder fileNumbers = new StringBuilder();
        List<List<Digit>> digits = getDigitsLines(fileName);

        for(List<Digit> digitLine: digits){
            if(fileNumbers.toString().length() > 0) {
                fileNumbers.append("\n");
            }
            for(Digit digit: digitLine){
                char number = digit.getNumberByKey();
                if(number== '?') {
                    fileNumbers.append(number+"ILL");
                } else {
                    fileNumbers.append(number);
                }
            }
        }

        return fileNumbers.toString();
    }

    private List<List<Digit>> getDigitsLines(String fileName) {
        List<String> lines = readFile(fileName);

        List<List<Digit>> digits = new ArrayList<>();

        for(int line=0;line < lines.size(); line+=3){
            List<Digit> digitLine = new ArrayList<>();

            for(int i=line; i<line+3; i++){
                List<String> splitStrings = splitLineIntoChunk(lines.get(i), 3);

                if(splitStrings.isEmpty()) break;

                for(int k=0 ; k < splitStrings.size(); k++) {
                    String chunk = splitStrings.get(k);
                    if (digitLine.isEmpty()  || digitLine.size()<(k+1)) {
                        digitLine.add(new Digit(chunk));
                    }else{
                        digitLine.get(k).getRows().add(chunk);
                    }
                }
            }
            digits.add(digitLine);

        }
        return digits;
    }

    private List<String> splitLineIntoChunk(String line, int chunkSize){
        List<String> chunks = new ArrayList<>();
        if(line.length() <= 27) {
            StringBuilder sb = new StringBuilder(line);

            while (!(sb.length() == 0) && (sb.length() >= chunkSize)) {

                chunks.add(sb.substring(0, chunkSize));

                sb.delete(0, chunkSize);
            }
        }
        return chunks;
    }
}
