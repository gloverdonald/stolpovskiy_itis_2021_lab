<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>WorldStat</title>
    <style>
        table {
            width: 300px;
            border: 1px solid green;
            margin: auto;
        }
        td {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    TreeMap<String, TreeMap<String, Integer>> w = (TreeMap<String, TreeMap<String, Integer>>) request.getAttribute("word");
    for (Map.Entry<String, TreeMap<String, Integer>> tw : w.entrySet()) { %>
<table border="1">
    <caption><%=tw.getKey()%>
    </caption>
    <%
        for (Map.Entry<String, Integer> word : tw.getValue().entrySet()) { %>
    <tr>
        <th><%=word.getKey()%></th>
        <th><%=word.getValue()%></th>
    </tr>
    <%
        } %>
</table>
<%
    }
%>
</body>
</html>