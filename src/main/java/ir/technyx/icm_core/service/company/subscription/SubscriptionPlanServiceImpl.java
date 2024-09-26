package ir.technyx.icm_core.service.company.subscription;

import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlan;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.mappers.company.subscription.SubscriptionMapper;
import ir.technyx.icm_core.repository.company.subscription.SubscriptionPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    @Transactional
    public ResSubscriptionPlanDto saveSubscriptionPlan(ReqSubscriptionPlanDto reqSubscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = SubscriptionMapper.toSubscriptionPlan(reqSubscriptionPlanDto);
        subscriptionPlanRepository.save(subscriptionPlan);
        return SubscriptionMapper.toResSubscriptionPlanDto(subscriptionPlan);
    }

    @Override
    @Transactional
    public ResSubscriptionPlanUpdatableDto updateSubscriptionPlan(ReqSubscriptionPlanUpdatableDto reqSubscriptionPlanUpdatableDto) {
        SubscriptionPlan subscriptionPlan = SubscriptionMapper.toSubscriptionPlan(reqSubscriptionPlanUpdatableDto);
        subscriptionPlanRepository.save(subscriptionPlan);
        return SubscriptionMapper.toResSubscriptionPlanUpdatableDto(subscriptionPlan);
    }

    @Override
    @Transactional
    public ResSubscriptionPlanUpdatableDto getSubscriptionPlanById(Long id) {
        return SubscriptionMapper
                .toResSubscriptionPlanUpdatableDto(subscriptionPlanRepository.findById(id)
                        .orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public ResSubscriptionPlanUpdatableDto getSubscriptionPlanByCode(String code) {
        return SubscriptionMapper
                .toResSubscriptionPlanUpdatableDto(subscriptionPlanRepository.findByCode(code)
                        .orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResSubscriptionPlanUpdatableDto> getAllSubscriptionPlan() {
        return SubscriptionMapper.toResSubscriptionPlanUpdatableDto(subscriptionPlanRepository.findAll());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subscriptionPlanRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(String code) {
        subscriptionPlanRepository.deleteByCode(code);
    }

}
