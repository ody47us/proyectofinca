// page component functionalities
$(window).scroll(() => {
    console.log("someting");
    if($(window).scrollTop() >= $('#navbar').height()){
        $('#navbar').addClass('fixed-top');
    }else{
        $('#navbar').remove('fixed-top');
    }
});