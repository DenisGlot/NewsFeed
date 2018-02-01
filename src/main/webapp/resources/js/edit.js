$(".editModalBtn").bind("click", edit);

function edit() {
    var $this = $(this);
    var title = $this.closest('div').find('.title').text();
    var content = $this.closest('div').find('.content').find("p").text();

    $("#myModalBtn").trigger("click");

    $("#name").val(title);
    $("#content").text($.trim(content));
}