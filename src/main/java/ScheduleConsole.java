import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScheduleConsole {

    public static void main(String[] args) {
        ArrayList<ArrayList> namesAndAssistance = readData();
        Map<String, Integer> coincidedOfficeTimes = findCoincidedTimes(namesAndAssistance);
        printCoincidedTimes(coincidedOfficeTimes);
    }

    public static ArrayList<ArrayList> readData() {
        ArrayList<ArrayList> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String dataLine = reader.readLine();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Map<Object, List<String>>> assistance = new ArrayList<>();
            Map<Object, List<String>> memberAssistance;
            String[] nameAndAssistance;
            while(!dataLine.isEmpty())
            {
                nameAndAssistance = dataLine.split("=");
                names.add(nameAndAssistance[0]);
                memberAssistance = Arrays.stream(nameAndAssistance[1].split(","))
                        .collect(Collectors.groupingBy(dayAndTimes -> dayAndTimes.substring(0,2)));
                assistance.add(memberAssistance);
                dataLine = reader.readLine();
            }
            data.add(names);
            data.add(assistance);
        }
        catch (IOException e)
        {
            System.out.println("Schedule-readData(): Couldn't read data");
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                System.out.println("Schedule-readData(): Couldn't close reader");
            }
        }
        return data;
    }

    public static Map<String,Integer> findCoincidedTimes(ArrayList namesAndAssistance)
    {
        Map<String,Integer> coincidedMembersTimes = new HashMap<>();
        ArrayList<String> names = (ArrayList<String>) namesAndAssistance.get(0);
        ArrayList<Map<Object, List<String>>> assistance = (ArrayList<Map<Object, List<String>>>) namesAndAssistance.get(1);
        int i = 0;
        int j = 1;
        Map<Object, List<String>> memberAssistance;
        Map<Object, List<String>> comparableMemberAssistance;
        int coincidedTimes = 0;
        String keyName = "";
        while(i < names.size() - 1)
        {
            memberAssistance = assistance.get(i);
            while(j < assistance.size())
            {
                comparableMemberAssistance = assistance.get(j);
                for(Object key : memberAssistance.keySet())
                {
                    if(comparableMemberAssistance.containsKey(key))
                    {
                        coincidedTimes = countCoincidedTimes(memberAssistance.get(key), comparableMemberAssistance.get(key));
                        if(coincidedTimes > 0)
                        {
                            keyName = "" + names.get(i) + "-" + names.get(j);
                            coincidedMembersTimes
                                    .put(keyName, coincidedMembersTimes.getOrDefault(keyName,0) + coincidedTimes);
                        }
                    }
                }
                j++;
            }
            i++;
            j = i + 1;
        }
        return coincidedMembersTimes;
    }

    public static int countCoincidedTimes(List<String> memberTimes1, List<String> memberTimes2)
    {
        int i = 0;
        int j = 0;
        int counter = 0;
        int earlierThanCurrent = 0;
        String currentTimeToCompare1;
        String currentTimeToCompare2;
        int comparison;
        while(i < memberTimes1.size())
        {
            currentTimeToCompare1 = memberTimes1.get(i);
            while(j < memberTimes2.size())
            {
                currentTimeToCompare2 = memberTimes2.get(j);
                comparison = compareTimes(currentTimeToCompare1,currentTimeToCompare2);
                if(comparison == 0)
                {
                    counter++;
                }
                else if (comparison == -1)
                {
                    earlierThanCurrent = j;
                }
                else
                {
                    break;
                }
                j++;
            }
            j = earlierThanCurrent;
            i++;
        }
        return counter;
    }

    public static int compareTimes(String time1, String time2)
    {
        int coincide = 0;
        List<Integer> hoursAndMinutes1 = Stream.of(time1.substring(2)
                        .replaceFirst("-",":").split(":"))
                .map(clockTime -> clockTime.contains(":") ? clockTime.replaceFirst(":","") : clockTime)
                .map(String::trim)
                .map(Integer::parseInt).toList();
        List<Integer> hoursAndMinutes2 = Stream.of(time2.substring(2)
                        .replaceFirst("-",":").split(":"))
                .map(clockTime -> clockTime.contains(":") ? clockTime.replaceFirst(":","") : clockTime)
                .map(String::trim)
                .map(Integer::parseInt).toList();
        int endHour1 = hoursAndMinutes1.get(2);
        int startHour2 = hoursAndMinutes2.get(0);
        if(endHour1 < startHour2)
        {
            coincide = 1;
        }
        else
        {
            int startHour1 = hoursAndMinutes1.get(0);
            int endHour2 = hoursAndMinutes2.get(2);
            if(endHour2 < startHour1)
            {
                coincide = -1;
            }
            else
            {
                if(startHour1 == endHour2 && endHour1 == startHour2)
                {
                    int endMinute1 = hoursAndMinutes1.get(3);
                    int startMinute2 = hoursAndMinutes2.get(1);
                    if(endMinute1 < startMinute2)
                    {
                        coincide = 1;
                    }
                    else
                    {
                        int startMinute1 = hoursAndMinutes1.get(1);
                        int endMinute2 = hoursAndMinutes2.get(3);
                        if (endMinute2 < startMinute1) {
                            coincide = -1;
                        }
                    }
                }
            }
        }
        return coincide;
    }

    public static void printCoincidedTimes(Map<String, Integer> coincidedTimes)
    {
        StringBuilder formattedFinalAnswer = new StringBuilder();
        if(coincidedTimes.isEmpty())
        {
            formattedFinalAnswer.append("No coinciding office times were found.");
        }
        else
        {
            String mapString = coincidedTimes.toString();
            Arrays.stream(mapString.substring(1, mapString.length() - 1).split(","))
                    .map(String::stripLeading)
                    .map(coincidedMembersTimes -> coincidedMembersTimes.replaceFirst("=",":"))
                    .forEach(formattedAnswer -> formattedFinalAnswer.append(formattedAnswer).append("\n"));
            int length = formattedFinalAnswer.length();
            formattedFinalAnswer.delete(length-1,length);
        }
        System.out.println(formattedFinalAnswer);
    }
}