
function addIngredient() {
    const container = document.getElementById("ingredients-container");
    const rowCount = container.rows.length;
    const newRow = document.createElement("tr");

    newRow.innerHTML = `
    <th><label >材料</label></th>
    <td><input type="text" class="input-form-short-recipes" name="ingredients[${rowCount}].name" required></td>
    <th><label >数量</label></th>
    <td><input type="text" class="input-form-short-recipes" name="ingredients[${rowCount}].amount" required></td>
    `;
    // ラベルを機能させたい場合
    // <th><label for="recipe-ingredients-${rowCount}">材料</label></th>
    // <td><input type="text" id="recipe-ingredients-${rowCount}" class="input-form-short-recipes" name="ingredients[${rowCount}].name"></td>
    // <th><label for="recipe-ingredients-amount-${rowCount}">数量</label></th>
    // <td><input type="text" id="recipe-ingredients-amount-${rowCount}" class="input-form-short-recipes" name="ingredients[${rowCount}].amount"></td>
    container.appendChild(newRow);

}
function addCooking() {
    const container = document.getElementById("cookings-container");
    const rowCount = container.rows.length;
    const newRow = document.createElement("tr");

    newRow.innerHTML = `
    <th><label >${rowCount + 1}</label></th><!-- 手順番号を表示 -->
        <td> <input type="hidden" name="cookings[${rowCount}].stepNumber" value="${rowCount + 1}"> <!-- stepNumberを非表示で送信 -->
   <textarea class="input-form-long-recipes" name="cookings[${rowCount}].description" required></textarea></td>

        `;
    // ラベルを機能させたい場合
    //   <th><label for="recipe-cookings-description-${rowCount}">${rowCount + 1}</label></th><!-- 手順番号を表示 -->
    //   <td> <input type="hidden" name="cookings[${rowCount}].stepNumber" value="${rowCount + 1}"> <!-- stepNumberを非表示で送信 -->
    //   <input type="text" id="recipe-cookings-description-${rowCount}" class="input-form-long-recipes" name="cookings[${rowCount}].description"></td>
    container.appendChild(newRow);

}
