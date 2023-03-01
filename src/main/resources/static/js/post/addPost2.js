// 1차업종 선택시 2차업종 설정
$('#firstCategory').change(function() {
    var first = $('#firstCategory').val();

    if(first == "1")
    {
        $('#firstCategory option').each(function() {
            if($(this).val() == 1)
                $(this).attr('selected', 'selected');
            else
                $(this).removeAttr('selected');
        });

        $('#secondCategory option').each(function()
        {
            if($(this).val() == 1) {
                $(this).text('(외식 전체)');
                $(this).removeAttr('hidden');
                $(this).attr('selected', 'selected');
            }
            else if($(this).val() > 3 && $(this).val() < 18)
                $(this).removeAttr('hidden');
            else {
                $(this).attr('hidden', 'hidden');
                $(this).removeAttr('selected');
            }
        });
    }
    if(first == "2")
    {
        $('#firstCategory option').each(function() {
            if($(this).val() == 2)
                $(this).attr('selected', 'selected');
            else
                $(this).removeAttr('selected');
        });

        $('#secondCategory option').each(function()
        {
            if($(this).val() == 2) {
                $(this).text('(도소매 전체)');
                $(this).removeAttr('hidden');
                $(this).attr('selected', 'selected');
            }
            else if($(this).val() >= 18 && $(this).val() < 24)
                $(this).removeAttr('hidden');
            else {
                $(this).attr('hidden', 'hidden');
                $(this).removeAttr('selected');
            }
        });
    }
    if(first == "3")
    {
        $('#firstCategory option').each(function() {
            if($(this).val() == 3)
                $(this).attr('selected', 'selected');
            else
                $(this).removeAttr('selected');
        });

        $('#secondCategory option').each(function()
        {
            if($(this).val() == 3) {
                $(this).text('(서비스 전체)');
                $(this).removeAttr('hidden');
                $(this).attr('selected', 'selected');
            }
            else if($(this).val() >= 24)
                $(this).removeAttr('hidden');
            else {
                $(this).attr('hidden', 'hidden');
                $(this).removeAttr('selected');
            }
        });
    }
});