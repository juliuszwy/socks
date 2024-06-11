function chooseItems(source, target,index) {
	var choiceOptions = source.options;
	var selectedOptions = target.options;
	for (i = 0; i < (choiceOptions.length); i++) {
		var temp = choiceOptions.item(i);
		if (temp.selected == true) {
			var oOption = document.createElement("OPTION");
			selectedOptions[selectedOptions.length] = new Option(temp.text,
					temp.value);
		}
	}

	for (j = (choiceOptions.length - 1); j >= 0; j--) {
		var temp = choiceOptions.item(j);
		if (temp.selected == true) {
			choiceOptions[j] = null;
		}
	}
	$("#total"+index+" option").unbind();
	$("#avail"+index+" option").unbind();
	$("#total"+index+" option").bind('dblclick', function(){	
		chooseItems(document.getElementById('total'+index), document.getElementById('avail'+index),index);
	});	
	$("#avail"+index+" option").bind('dblclick', function(){	
		chooseItems(document.getElementById('avail'+index), document.getElementById('total'+index),index);
	});	

}
function chooseItems2(source, target) {
	var choiceOptions = source.options;
	var selectedOptions = target.options;
	for (i = 0; i < (choiceOptions.length); i++) {
		var temp = choiceOptions.item(i);
		if (temp.selected == true) {
			var oOption = document.createElement("OPTION");
			selectedOptions[selectedOptions.length] = new Option(temp.text,
					temp.value);
		}
	}

	for (j = (choiceOptions.length - 1); j >= 0; j--) {
		var temp = choiceOptions.item(j);
		if (temp.selected == true) {
			choiceOptions[j] = null;
		}
	}
	$("#total2 option").unbind();
	$("#avail2 option").unbind();
	$("#total2 option").bind('dblclick', function(){	
		chooseItems2(conditionForm.total2, conditionForm.avail2);
	});	
	$("#avail2 option").bind('dblclick', function(){	
		chooseItems2(conditionForm.avail2, conditionForm.total2);
	});	

}

function adjustUp(source) {
	var itemOptions = conditionForm.avail.options;
	var selectedOption;
	var index;
	for (var i = 0; i < itemOptions.length; i++) {
		var temp = itemOptions.item(i);
		if (temp.selected == true) {
			index = i;
			selectedOption = itemOptions[index];
			var lastOption = itemOptions[index - 1];
			var temp = new Option(selectedOption.text, selectedOption.value);

			selectedOption.text = lastOption.text;
			selectedOption.value = lastOption.value;
			selectedOption.selected = false;

			lastOption.text = temp.text;
			lastOption.value = temp.value;
			lastOption.selected = true;
		}
	}

}
function adjustUp2(source) {
	var itemOptions = conditionForm.avail2.options;
	var selectedOption;
	var index;
	for (var i = 0; i < itemOptions.length; i++) {
		var temp = itemOptions.item(i);
		if (temp.selected == true) {
			index = i;
			selectedOption = itemOptions[index];
			var lastOption = itemOptions[index - 1];
			var temp = new Option(selectedOption.text, selectedOption.value);

			selectedOption.text = lastOption.text;
			selectedOption.value = lastOption.value;
			selectedOption.selected = false;

			lastOption.text = temp.text;
			lastOption.value = temp.value;
			lastOption.selected = true;
		}
	}

}

function adjustDown(source) {
	var itemOptions = conditionForm.avail.options;
	var selectedOption;
	var index;

	for (var i = itemOptions.length - 1; i >= 0; i--) {
		var temp = itemOptions.item(i);
		if (temp.selected == true) {
			index = i;
			selectedOption = itemOptions[index];
			var nextOption = itemOptions[index + 1];
			var temp = new Option(selectedOption.text, selectedOption.value);

			selectedOption.text = nextOption.text;
			selectedOption.value = nextOption.value;
			selectedOption.selected = false;

			nextOption.text = temp.text;
			nextOption.value = temp.value;
			nextOption.selected = true;
		}
	}

}
function adjustDown2(source) {
	var itemOptions = conditionForm.avail2.options;
	var selectedOption;
	var index;

	for (var i = itemOptions.length - 1; i >= 0; i--) {
		var temp = itemOptions.item(i);
		if (temp.selected == true) {
			index = i;
			selectedOption = itemOptions[index];
			var nextOption = itemOptions[index + 1];
			var temp = new Option(selectedOption.text, selectedOption.value);

			selectedOption.text = nextOption.text;
			selectedOption.value = nextOption.value;
			selectedOption.selected = false;

			nextOption.text = temp.text;
			nextOption.value = temp.value;
			nextOption.selected = true;
		}
	}

}

function checkForm(form1) {
	var columnsOptions = form1.selectcolumns.options;

	for (var i = 0; i < columnsOptions.length; i++) {
		columnsOptions[i].selected = true;
	}

	return true;
}
