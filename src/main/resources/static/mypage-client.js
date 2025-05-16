const container = document.querySelector('.mypage-container');

const sectionTitle = document.createElement('h3');
sectionTitle.textContent = '프로젝트 등록 현황';
sectionTitle.style.marginTop = '30px';

const sectionBox = document.createElement('div');
sectionBox.style.padding = '15px';
sectionBox.style.border = '1px solid #ccc';
sectionBox.style.borderRadius = '8px';
sectionBox.style.backgroundColor = '#f4f4f4';
sectionBox.style.marginTop = '10px';

const userId = document.getElementById('user-id').textContent;

fetch(`/api/clients/projects?userId=${encodeURIComponent(userId)}`, {
  method: 'GET',
  credentials: 'same-origin'
})
.then(res => {
  if (!res.ok) throw new Error('응답 실패');
  return res.json();
})
.then(projects => {
  sectionBox.innerHTML = '';
  if (projects.length === 0) {
    sectionBox.textContent = '아직 등록한 프로젝트가 없습니다.';
  } else {
    const cardContainer = document.createElement('div');
    cardContainer.className = 'card-container';

    projects.forEach(proj => {
      const card = document.createElement('div');
      card.className = 'card';
      const date = new Date(proj.createdDate).toLocaleString('ko-KR');
      card.textContent = `${proj.projectTitle} (등록일: ${date})`;
      cardContainer.appendChild(card);
      
      card.addEventListener('click', () => {
        showProjectModal(proj);
      });
    });

    sectionBox.appendChild(cardContainer);
  }
})
.catch(err => {
  console.error('프로젝트 등록 내역 불러오기 실패:', err);
  sectionBox.textContent = '등록 내역을 불러오는 중 오류가 발생했습니다.';
});

container.appendChild(sectionTitle);
container.appendChild(sectionBox);
