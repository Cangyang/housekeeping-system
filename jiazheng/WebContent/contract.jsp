<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
#contractTitle {
    text-align: center;
}
.data {
    text-decoration: underline;
}
.table {
    border-collapse: collapse;
    border: 1px solid;
    width: 300px;
}
.table tr {
    border: 1px solid;
}
.table tr td {
    border: 1px solid;
    height: 20px;
    text-align: center;
}
</style>
<body>
<h2 id="contractTitle">家政服务协议书</h2>
<p>甲方（雇主）：<span class="data" id="gzName"></span></p>
<p>乙方（家政员）：<span class="data" id="bmName"></span></p>
<p>甲乙双方根据平等自愿的原则，经协商签订如下协议，愿意共同遵守。</p>
<p>一、乙方愿意为甲方提供<span class="data" id="servType"></span>为内容的家政服务工作。</p>
<table class="table">
<tr><td>食宿条件</td><td><span class="data" id="isAccomm"></span></td></tr>
<tr><td>工作时间</td><td><span class="data" id="workTime"></span></td></tr>
<tr><td>服务期限</td><td><span class="data" id="beginDate"></span>至<span class="data" id="endDate"></span></td></tr>
</table>
<p>&nbsp;&nbsp;&nbsp;&nbsp;未经乙方同意，甲方不能擅自增加上述规定以外的服务内容。</p>
<p>二、甲方应尊重乙方的人格和劳动，平等待人，若乙方不能按协议要求完成工作，甲方有权辞退乙方。双方应<br>&nbsp;&nbsp;&nbsp;&nbsp;各自遵守《家政服务员的职业道德》及《雇主应履行的义务》。</p>
<p>三、乙方在合同期内患病，甲方应允许其外出看病，并与乙方协商应病误工的解决办法。</p>
<p>四、甲方在每月应按时向乙方支付上月的工资<span class="data" id="gzPay"></span>元。</p>
<p>五、乙方在服务过程中，造成本人或他人的意外事故，甲方应立即通知乙方家属及有关部门，积极处理好善后<br>&nbsp;&nbsp;&nbsp;&nbsp;事宜，并按法律承担相应的责任。</p>
<p>六、乙方在服务过程中，因工作失误给甲方造成损失的，甲方可追究乙方责任，并有权要求乙方承担相应负责<br>&nbsp;&nbsp;&nbsp;&nbsp;人的经济赔偿。在任何情况下，甲方都不得采取搜身、扣押钱财以及殴打、威逼等侵权行为。</p>
<p>七、乙方有权拒绝为他人服务或被带往外省市服务。</p>
<p>八、甲乙双方如发生纠葛、争议和其它事故，可先由见证机构进行调解，如无效，可向具有管辖权的法院提起<br>&nbsp;&nbsp;&nbsp;&nbsp;民事诉讼。</p>
<p>九、一方要求提前解除本协议，应提前7天以口头或书面形式通知另一方。</p>
<p>十、本协议未尽事宜，双方共同协商。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;甲方：<span class="data" id="gzSignature"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方：<span class="data" id="bmSignature"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见证机构：<span class="data">家家乐家政服务网</span></p>
<p><br>&nbsp;&nbsp;&nbsp;&nbsp;日期：<span class="data" id="gzSigDate"></span>&nbsp;&nbsp;日期：<span class="data" id="bmSigDate"></span>&nbsp;&nbsp;日期：<span class="data" id="sigDate"></span></p>