<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>DashBoard</title>
</head>
<body>
<div style="WIDTH:820; font-size: 20px;" align="CENTER"><u>DashBoard</u></div>
<BR><#assign aDateTime = .now>
<#assign aDate = aDateTime?date>
<#assign aTime = aDateTime?time>
<div style="WIDTH:820" align="RIGHT">${aDate?iso_utc} ${aTime?iso_utc}</div>

<div style="WIDTH:820;font-size: 15px;" align="LEFT">▶ Transcoder List</div>
<div style="WIDTH:820px; font-size: 15px;">
    <table width="100%" border="1" style="BORDER-COLLAPSE:COLLAPSE;" align="center">
        <thead>
        <tr>
            <th>IP</th>
            <th>PORT</th>
            <th>TYPE</th>
            <th>SYSTEM LOAD</th>
            <th>UPDATED</th>
        </tr>
        </thead>
        <tbody>
        <#list list_transcoder as transcoder>
        <TR>
            <TD align="center">${transcoder.serviceIp?string}</TD>
            <TD align="center">${transcoder.servicePort?long?c}</TD>
            <TD align="center">${transcoder.type}</TD>
            <TD align="center">${transcoder.systemLoad?c}</TD>
            <TD align="center">${transcoder.updatedTime?date} ${transcoder.updatedTime?time}</TD>
        </TR>
        </#list>
        </tbody>
    </table>
</div>
<BR>
<div style="WIDTH:820;font-size: 15px;" align="LEFT">▶ Job List</div>
<div style="WIDTH:820px; font-size: 15px;">
    <table width="100%" border="1" style="BORDER-COLLAPSE:COLLAPSE;" align="center">
        <TR>
            <TH align="center">MovieNo</TH>
            <TH align="center">UserNo</TH>
            <TH align="center">ContentType</TH>
            <TH align="center">EncoderIp</TH>
            <TH align="center">Mobile128K</TH>
            <TH align="center">400x300</TH>
            <TH align="center">640x480</TH>
            <TH align="center">1280x720</TH>
            <TH align="center">CREATED</TH>
            <TH align="center">UPDATED</TH>
        </TR>
    <#list list_job as jobInfo>
        <TR>
            <TD align="center">${jobInfo.movieNo?long?c}</TD>
            <TD align="center">${jobInfo.userNo?long?c}</TD>
            <TD align="center">${jobInfo.contentsType}</TD>
            <TD align="center"><#if jobInfo.encoderIp??> ${jobInfo.encoderIp} </#if></TD>
            <TD align="center">${jobInfo.progressMobile128K}</TD>
            <TD align="center">${jobInfo.progress400X300}</TD>
            <TD align="center">${jobInfo.progress640X480}</TD>
            <TD align="center">${jobInfo.progress1280X720}</TD>
            <TD><#if jobInfo.created??>${jobInfo.created?date} ${jobInfo.created?time}</#if></TD>
            <TD><#if jobInfo.updated??>${jobInfo.updated?date} ${jobInfo.updated?time}</#if></TD>
        </TR>
    </#list>
    </TABLE>
</div>
</body>
</html>