package com.ruoyi.common.utils;

import org.apache.poi.ss.usermodel.PaperSize;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author sett
 * @date 2023年04月05日 18:57
 * @title
 */
public class JudgeUtils {

    public static List<String> Positive1 = Arrays.asList("1","3","4","7","8","9","10","13","15","19");

    public static List<String> Positive2 = Arrays.asList("1","2","3","4","6","7","8","10","11","12","14","15","16","18","20");

    public static List<String> Positive3 = Arrays.asList("2","3","4","7","8","11","12","13","14","17","18");



    public static List<String> Answer1 = Arrays.asList(
            "无抑郁",
            "轻度抑郁",
            "中度抑郁",
            "重度抑郁"
    );
    public static List<String> Answer2 = Arrays.asList(
            "无明显焦虑",
            "轻度焦虑",
            "中度焦虑",
            "重度焦虑"
    );
    public static List<String> Answer3 = Arrays.asList(
            "低级孤独",
            "一般偏下孤独",
            "中间水平孤独",
            "一般偏上孤独",
            "高度孤独"
    );
    public static List<String> Suggestion1 = Arrays.asList(
            "恭喜您！但是，需要注意的是，抑郁是一种常见的心理健康问题，它可能会在未来出现。因此，保持积极的心态，关注自己的情绪变化，并寻求专业帮助，以便及时应对任何可能的心理健康问题。",
            "建议您采取一些积极的措施来缓解情绪，例如进行适度的运动，保持健康的饮食习惯，和亲友沟通交流等。此外，您也可以寻求专业帮助，例如心理咨询师或精神科医生的帮助，他们会为您提供更具体的建议和治疗方案。",
            "建议您尽快寻求专业帮助，例如心理咨询师或精神科医生的帮助。他们会为您制定个性化的治疗方案，例如药物治疗、认知行为疗法、心理治疗等。同时，您也可以采取一些积极的措施来缓解情绪，例如进行适度的运动，保持健康的饮食习惯，和亲友沟通交流等。",
            "建议您立即寻求专业的医疗帮助。重度抑郁可能会影响您的日常生活和工作，需要专业的治疗和支持。您可以咨询精神科医生或心理医生，他们会为您提供紧急的治疗方案，例如药物治疗、心理治疗、住院治疗等。此外，建议您寻求亲友的支持和帮助，与他们沟通交流，共同面对抑郁症带来的挑战。"
    );

    public static List<String> Suggestion2 = Arrays.asList(
            "请继续保持良好的心理状态，注重自我调节和心理保健，积极参与社交活动和运动锻炼等，保持心态平和，以避免潜在的焦虑症状发展。",
            "建议采取一些行为和心理方面的措施来缓解焦虑，例如进行深呼吸、放松训练等，可以试着调整生活习惯，如规律作息、良好的饮食、运动等，同时寻求专业心理咨询师的帮助，进行适当的心理治疗。",
            "建议及时采取措施以避免病情进一步恶化，可以采取药物治疗或心理治疗，同时避免过度的压力和负面情绪，例如保持情绪稳定，避免消极思维等，尽可能的寻求家人、朋友或专业心理医师的支持。",
            "建议及时寻求专业心理咨询和医疗服务，因为重度焦虑会严重影响人的正常生活和工作，需要采取更加积极的治疗措施，如药物治疗、心理治疗和行为治疗等。同时，也需要家人和社会的支持和帮助，以帮助焦虑患者渡过难关。"
    );    public static List<String> Suggestion3 = Arrays.asList(
            "如果您被诊断为低级孤独，您可能会感觉有些孤单，但这并不一定是一种严重的问题。您可以考虑参加社交活动，与家人和朋友保持联系，或者尝试新的爱好和兴趣爱好，以增加社交互动和建立更多的人际关系。",
            "如果您被诊断为一般偏下孤独，您可能感到比低级孤独更加孤独和失落。您可以考虑加入社交团体，参加志愿者活动或社区活动，或寻找一个支持小组或治疗机构，寻求专业的帮助和支持。",
            "如果您被诊断为中间水平孤独，您可能感到相对孤独，但仍有一定的社交联系。您可以考虑加强您的社交互动，参加社交活动、旅行或参加课程学习，以扩大您的社交圈子。",
            "如果您被诊断为一般偏上孤独，您可能感到非常孤独和失落。您可以考虑寻找支持小组、治疗机构或心理医生的帮助，与其他人分享您的经历，寻找专业的建议和支持。",
            "如果您被诊断为高度孤独，您可能感到极度孤独和绝望。您需要立即寻求专业的帮助和支持，寻找治疗机构或心理医生，以便获得紧急治疗和支持。如果您感到无法控制自己的情绪或想法，请立即拨打紧急电话或寻求其他紧急支持措施。"
    );


    public static List<String> Judge1(Map<String,Object>map){
        Integer result = 0 ;
        List<String> res = new ArrayList<>();
        res.add(String.valueOf(map.get("submit")));
        map.remove("submit");
        for(String s:map.keySet()){
            if(Positive1.contains(s)){
                result  = result + Integer.parseInt((String) map.get(s));
            }else{
                result = result + (5-Integer.parseInt((String) map.get(s)));
            }
        }
        Long l = Math.round(result * 1.25);
        res.add(l.toString());
        if(l<53l){
            res.add(Answer1.get(0));
            res.add(Suggestion1.get(0));
        }else if(l<63l){
            res.add(Answer1.get(1));
            res.add(Suggestion1.get(1));
        }else if(l<72l){
            res.add(Answer1.get(2));
            res.add(Suggestion1.get(2));
        }else {
            res.add(Answer1.get(2));
            res.add(Suggestion1.get(2));
        }
        return res;
    }

    public static List<String> Judge2(Map<String,Object>map){
        Integer result = 0 ;
        List<String> res = new ArrayList<>();
        res.add(String.valueOf(map.get("submit")));
        map.remove("submit");
        for(String s:map.keySet()){
            if(Positive2.contains(s)){
                result  = result + Integer.parseInt((String) map.get(s));
            }else{
                result = result + (5-Integer.parseInt((String) map.get(s)));
            }
        }
        Long l = Math.round(result * 1.25);
        res.add(l.toString());
        if(l<50l){
            res.add(Answer2.get(0));
            res.add(Suggestion2.get(0));
        }else if(l<60l){
            res.add(Answer2.get(1));
            res.add(Suggestion2.get(1));
        }else if(l<70l){
            res.add(Answer2.get(2));
            res.add(Suggestion2.get(2));
        }else {
            res.add(Answer2.get(2));
            res.add(Suggestion2.get(2));
        }
        return res;
    }
    public static List<String> Judge3(Map<String,Object>map){
        Integer result = 0 ;
        List<String> res = new ArrayList<>();
        res.add(String.valueOf(map.get("submit")));
        map.remove("submit");
        for(String s:map.keySet()){
            if(Positive2.contains(s)){
                result  = result + Integer.parseInt((String) map.get(s));
            }else{
                result = result + (5-Integer.parseInt((String) map.get(s)));
            }
        }
        res.add(result.toString());
        if(result<28){
            res.add(Answer3.get(0));
            res.add(Suggestion3.get(0));
        }else if(result<33){
            res.add(Answer3.get(1));
            res.add(Suggestion3.get(1));
        }else if(result<39){
            res.add(Answer3.get(2));
            res.add(Suggestion3.get(2));
        }else if(result<44){
            res.add(Answer3.get(3));
            res.add(Suggestion3.get(3));
        }else{
            res.add(Answer3.get(4));
            res.add(Suggestion3.get(4));
        }
        return res;
    }
}
