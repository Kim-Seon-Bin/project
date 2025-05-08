fetch('/api/freelancers')
  .then(response => response.json())
  .then(data => {
    const list = document.getElementById('freelancer-list');
    data.forEach(freelancer => {
      const card = document.createElement('div');
      card.className = 'freelancer-card';
      card.innerHTML = `
        <h2>${freelancer.freelancerName}</h2>
        <p>${freelancer.freelancerIntro}</p>
        <p><strong>포트폴리오:</strong> ${freelancer.freelancerPortfolio}</a></p>
        <div><strong>보유 스킬:</strong> ${freelancer.freelancerSkills.join(' ')}</div>
      `;
      list.appendChild(card);
    });
  });
