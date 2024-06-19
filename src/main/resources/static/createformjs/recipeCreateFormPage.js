$(document).ready(function () {
  $(".star_rating > .star").click(function () {
    $(this).parent().children("span").removeClass("on");
    $(this).addClass("on").prevAll("span").addClass("on");

    // 새로운 코드: 클릭된 별의 값을 input에 설정
    const selectedValue = $(this).data("value");
    $("#recipeLevelInput").val(selectedValue);
  });


  const maxFields = 30;

  const createAddRemoveHandler = (wrapperSelector, addButtonSelector, fieldHtml) => {
    const wrapper = $(wrapperSelector);
    const addButton = $(addButtonSelector);
    let fieldCount = wrapper.children().length;

    addButton.click(function (e) {
      e.preventDefault();
      if (fieldCount < maxFields) {
        fieldCount++;
        wrapper.append(fieldHtml(fieldCount));
      }
    });

    wrapper.on("click", ".minus_btn", function (e) {
      e.preventDefault();
      $(this).parent().remove();
      fieldCount--;
    });
  };

  createAddRemoveHandler("#ingredients", "#plus_btn_1", (index) => `
    <div class="ingredients_name hide_on_btn">
      <input class="main" type="text" name="ingredients[]" placeholder="ex) 김치">
      <input class="sub" type="text" name="ingredientUnits[]" placeholder=" g,ml(단위)">
      <button class="minus_btn" type="button"><i class="fa-solid fa-circle-xmark"></i></button>
    </div>
  `);

  createAddRemoveHandler("#seasoning", "#plus_btn_2", (index) => `
    <div class="seasoning_name hide_on_btn">
      <input class="main" type="text" name="seasonings[]" placeholder=" ex) 고추장">
      <input class="sub" type="text" name="seasoningUnits[]" placeholder="  g,ml(단위)">
      <button class="minus_btn" type="button"><i class="fa-solid fa-circle-xmark"></i></button>
    </div>
  `);

  let stepCounter = 1;

  const addStep = () => {
    const wrapper = $("#steps");
    stepCounter++;
    const stepHtml = `
      <div class="make_step" data-index="${stepCounter - 1}">
        <div class="top_bar_step">
          <div class="step_name_bar">Step${stepCounter}</div>
          <div class="step_text_bar">
            <textarea name="steps[${stepCounter - 1}].stepText" placeholder="ex) 소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요."></textarea>
          </div>
          <div class="step_img_bar">
            <div class="PhotoBox" id="divStepPhotoBox_${stepCounter}" is_over="0">
              <div class="input_1">
                <img id="preview${stepCounter}" src="https://github.com/Junsu-Ahn/cookers/assets/156152342/aed09051-deec-48df-a4eb-3114ba0b27d6" style="width: 236px; height: 236px;">
              </div>
              <input type="file" name="stepImages" accept="image/png, image/gif, image/jpeg" onchange="readURL(this, 'preview${stepCounter}');">
            </div>
          </div>
        </div>
        <div class="top_bar_tip">
          <div class="tip_name_bar"><i class="fa-solid fa-star"></i> 꿀 팁</div>
          <div class="tip_text_bar">
            <textarea name="steps[${stepCounter - 1}].stepTipText" placeholder="ex) 고기요리는 소금보다 설탕을 먼저 넣어야 단맛이 겉돌지 않고 육질이 부드러워져요"></textarea>
          </div>
        </div>
        <button class="step_del_btn" type="button" value="삭제"><i class="fa-solid fa-circle-xmark"></i></button>
      </div>
    `;
    wrapper.append(stepHtml);
    updateStepNumbers();
  };

  $(".step_add_btn").click(function (e) {
    e.preventDefault();
    addStep();
  });

  $("#steps").on("click", ".step_del_btn", function (e) {
    e.preventDefault();
    $(this).closest(".make_step").remove();
    updateStepNumbers();
  });

  function updateStepNumbers() {
    let currentStep = 1;
    $(".make_step").each(function () {
      $(this).attr("data-index", currentStep - 1);
      $(this).find(".step_name_bar").text(`Step${currentStep}`);
      $(this).find(".step_text_bar textarea").attr("name", `steps[${currentStep - 1}].stepText`);
      $(this).find(".step_img_bar input[type='file']").attr("name", `stepImages`);
      $(this).find(".step_img_bar input[type='file']").attr("onchange", `readURL(this, 'preview${currentStep}');`);
      $(this).find(".step_img_bar img").attr("id", `preview${currentStep}`);
      $(this).find(".top_bar_tip textarea").attr("name", `steps[${currentStep - 1}].stepTipText`);
      currentStep++;
    });
    stepCounter = currentStep;
  }

  window.readURL = function (input, previewId) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        $(`#${previewId}`).attr("src", e.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      $(`#${previewId}`).attr("src", '');
    }
  };
});