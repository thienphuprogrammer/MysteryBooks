<div class="container">
    <input type="file" multiple="multiple" accept="image/jpeg,image/png,image/gif, image/jpg"
           name="urlImages"
           id="fileImage"
    >
    <output id="list-image" class="d-flex flex-wrap justify-content-center align-items-center"
            style="overflow: auto; max-height: 500px; position: relative;"
    ></output>
</div>
<script>
    const fileImage = document.getElementById('fileImage');
    const listImage = document.getElementById('list-image');
    const imageArray = [];
    fileImage.addEventListener('change', function (e) {
        const files = fileImage.files;
        for (let i = 0; i < files.length; i++) {
            imageArray.push(files[i]);
        }
        displayImage();
    });

    function displayImage() {
        listImage.innerHTML = '';
        for (let i = 0; i < imageArray.length; i++) {
            const div = document.createElement('div');
            div.style.position = 'relative';
            div.style.display = 'inline-block';

            const image = document.createElement('img');
            image.src = URL.createObjectURL(imageArray[i]);
            image.height = 100;
            image.style.position = 'relative';
            image.className = 'img-thumbnail m-1 p-1 border-0 rounded';
            div.appendChild(image);

            const span = document.createElement('span');
            span.className = 'btn btn-danger';
            span.innerHTML = 'X';
            span.style.position = 'absolute';
            span.style.top = '0';
            span.style.right = '0';
            span.style.cursor = 'pointer';
            span.style.zIndex = '100';
            span.addEventListener('click', function () {
                deleteImage(i);
            });
            div.appendChild(span);
            listImage.appendChild(div);
        }
    }

    function deleteImage(index) {
        imageArray.splice(index, 1);
        displayImage();
    }
</script>